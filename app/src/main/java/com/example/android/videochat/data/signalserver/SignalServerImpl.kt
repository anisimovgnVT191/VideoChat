package com.example.android.videochat.data.signalserver

import com.example.android.videochat.data.models.IceCandidateModel
import com.example.android.videochat.data.models.OfferModel
import com.example.android.videochat.presentation.models.SessionOfferType
import com.example.android.videochat.presentation.models.UserType
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SignalServerImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : SignalServer {
    private val sendOffersSubject = BehaviorSubject.create<Unit>()
    private val getOffersSubject = BehaviorSubject.create<OfferModel>()

    private val sendIceCandidateSubject = BehaviorSubject.create<Unit>()
    private val getIceCandidatesSubject = BehaviorSubject.create<IceCandidateModel>()

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

    override fun sendIceCandidate(
        callId: String,
        userType: UserType,
        iceCandidate: IceCandidateModel
    ): BehaviorSubject<Unit> {
        val iceCandidateDb = HashMap<String, Any>().apply {
            put(CANDIDATE_SDP_CANDIDATE_FIELD, iceCandidate.sdp)
            put(CANDIDATE_SDP_M_LINE_INDEX_FIELD, iceCandidate.sdpMLineIndex)
            put(CANDIDATE_SDP_MID_FIELD, iceCandidate.sdpMid)
            put(CANDIDATE_SERVER_URL_FIELD, iceCandidate.serverUrl)
            put(
                CANDIDATE_TYPE_FIELD, when (userType) {
                    UserType.CALLER -> CANDIDATES_COLLECTION_OFFER_ID
                    UserType.CALLEE -> CANDIDATES_COLLECTION_ANSWER_ID
                }
            )
        }

        firestore.collection(CALLS_COLLECTION)
            .document(callId)
            .collection(CANDIDATES_COLLECTION)
            .document(
                when (userType) {
                    UserType.CALLER -> CANDIDATES_COLLECTION_OFFER_ID
                    UserType.CALLEE -> CANDIDATES_COLLECTION_ANSWER_ID
                }
            )
            .set(iceCandidateDb)
            .addOnSuccessListener {
                sendIceCandidateSubject.onNext(Unit)
            }
            .addOnFailureListener {
                sendIceCandidateSubject.onError(IllegalStateException())
            }

        return sendIceCandidateSubject
    }

    override fun getIceCandidate(
        callId: String,
        userType: UserType
    ): BehaviorSubject<IceCandidateModel> {
        firestore.collection(CALLS_COLLECTION)
            .document(callId)
            .collection(CANDIDATES_COLLECTION)
            .document(
                when (userType) {
                    UserType.CALLER -> CANDIDATES_COLLECTION_ANSWER_ID
                    UserType.CALLEE -> CANDIDATES_COLLECTION_OFFER_ID
                }
            )
            .addSnapshotListener { value, error ->
                if (error != null) {
                    getIceCandidatesSubject.onError(IllegalStateException())
                }

                value?.let { snapshot ->
                    if (snapshot.data != null) {
                        getIceCandidatesSubject.onNext(
                            IceCandidateModel(
                                sdp = snapshot.data!![CANDIDATE_SDP_CANDIDATE_FIELD]!! as String,
                                sdpMLineIndex = (snapshot.data!![CANDIDATE_SDP_M_LINE_INDEX_FIELD]!! as Long).toInt(),
                                sdpMid = snapshot.data!![CANDIDATE_SDP_MID_FIELD]!! as String,
                                serverUrl = snapshot.data!![CANDIDATE_SERVER_URL_FIELD]!! as String
                            )
                        )
                    }
                } ?: getIceCandidatesSubject.onError(java.lang.IllegalStateException())
            }

        return getIceCandidatesSubject
    }

    companion object {
        private const val CALLS_COLLECTION = "calls"

        private const val CALL_SDP_FIELD = "sdp"
        private const val CALL_TYPE_FIELD = "type"

        private const val CANDIDATES_COLLECTION = "candidates"

        private const val CANDIDATES_COLLECTION_OFFER_ID = "offerCandidate"
        private const val CANDIDATES_COLLECTION_ANSWER_ID = "answerCandidate"

        private const val CANDIDATE_SDP_CANDIDATE_FIELD = "sdpCandidate"
        private const val CANDIDATE_SDP_M_LINE_INDEX_FIELD = "sdpMLineIndex"
        private const val CANDIDATE_SDP_MID_FIELD = "sdpMid"
        private const val CANDIDATE_SERVER_URL_FIELD = "serverUrl"
        private const val CANDIDATE_TYPE_FIELD = "type"
    }
}