package com.example.android.videochat.data.signalserver

import com.example.android.videochat.data.models.OfferModel
import com.example.android.videochat.presentation.models.SessionOfferType
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.subjects.BehaviorSubject
import java.lang.IllegalStateException
import javax.inject.Inject

class SignalServerImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : SignalServer {
    private val sendOffersSubject = BehaviorSubject.create<Unit>()
    private val getOffersSubject = BehaviorSubject.create<OfferModel>()

    override fun sendCallOffer(
        callId: String,
        sdp: String,
        type: SessionOfferType
    ): BehaviorSubject<Unit> {
        val callData = HashMap<String, String>().apply {
            put(CALL_SDP_FIELD, sdp)
            put(CALL_TYPE_FIELD, type.name)
        }

        firestore.collection(CALLS_COLLECTION)
            .document(callId)
            .set(callData)
            .addOnSuccessListener {
                sendOffersSubject.onNext(Unit)
            }
            .addOnFailureListener {
                sendOffersSubject.onError(IllegalAccessException())
            }

        return sendOffersSubject
    }

    override fun getCallOffer(callId: String, type: SessionOfferType): BehaviorSubject<OfferModel> {
        firestore.collection(CALLS_COLLECTION)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    getOffersSubject.onError(IllegalStateException())
                }

                value?.documents?.find { documentSnapshot ->
                    if (documentSnapshot.id != callId) {
                        return@find false
                    }
                    val callType =
                        documentSnapshot.data?.get(CALL_TYPE_FIELD) as? String ?: return@find false

                    return@find SessionOfferType.valueOf(callType) == type
                }?.let { document ->
                    getOffersSubject.onNext(
                        OfferModel(
                            sdp = document.data!!.getOrDefault(CALL_SDP_FIELD, "") as String,
                            type = SessionOfferType.valueOf(
                                document.data!!.getOrDefault(
                                    CALL_TYPE_FIELD, "OFFER"
                                ) as String
                            )
                        )
                    )
                }
            }

        return getOffersSubject
    }

    companion object {
        private const val CALLS_COLLECTION = "calls"

        private const val CALL_SDP_FIELD = "sdp"
        private const val CALL_TYPE_FIELD = "type"
    }
}