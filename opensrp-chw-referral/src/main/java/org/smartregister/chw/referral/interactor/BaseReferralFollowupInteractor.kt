package org.smartregister.chw.referral.interactor

import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import com.nerdstone.neatformcore.domain.model.NFormViewData
import org.json.JSONObject
import org.koin.core.inject
import org.smartregister.chw.referral.ReferralLibrary
import org.smartregister.chw.referral.contract.BaseFollowupContract
import org.smartregister.chw.referral.util.Constants
import org.smartregister.chw.referral.util.JsonFormUtils
import timber.log.Timber
import java.util.*


/**
 * This interactor class provides actual implementations for all the functionality used in the
 * follow up referral forms, it implements [BaseFollowupContract.Interactor]
 */
class BaseReferralFollowupInteractor : BaseFollowupContract.Interactor {

    val referralLibrary by inject<ReferralLibrary>()

    @Throws(Exception::class)
    override fun saveFollowup(
        baseEntityId: String, valuesHashMap: HashMap<String, NFormViewData>,
        jsonObject: JSONObject, callBack: BaseFollowupContract.InteractorCallBack
    ) = saveFollowup(baseEntityId, valuesHashMap, jsonObject)

    @VisibleForTesting
    fun saveFollowup(
        baseEntityId: String?, valuesHashMap: HashMap<String, NFormViewData>?,
        jsonObject: JSONObject?
    ) {
        val baseEvent =
            JsonFormUtils.processJsonForm(
                referralLibrary, baseEntityId, valuesHashMap!!, jsonObject,
                Constants.EventType.REGISTRATION
            ).event
        baseEvent.eventId = UUID.randomUUID().toString()
        Timber.i("Followup Event = %s", Gson().toJson(baseEvent))
        //        Util.processEvent(allSharedPreferences, baseEvent);
    }
}