package org.smartregister.chw.referral.presenter

import android.app.Activity
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.ViewModel
import com.nerdstone.neatformcore.domain.model.NFormViewData
import org.apache.commons.lang3.tuple.Triple
import org.json.JSONException
import org.json.JSONObject
import org.smartregister.chw.referral.R
import org.smartregister.chw.referral.contract.BaseIssueReferralContract
import org.smartregister.chw.referral.domain.MemberObject
import org.smartregister.chw.referral.model.AbstractIssueReferralModel
import org.smartregister.chw.referral.util.Constants
import org.smartregister.chw.referral.util.DBConstants
import org.smartregister.util.Utils
import timber.log.Timber
import java.lang.ref.WeakReference
import java.util.*

open class BaseIssueReferralPresenter(
        val baseEntityID: String,
        view: BaseIssueReferralContract.View,
        private val viewModelClass: Class<out AbstractIssueReferralModel>,
        protected var interactor: BaseIssueReferralContract.Interactor
) : BaseIssueReferralContract.Presenter, BaseIssueReferralContract.InteractorCallBack {

    var memberObject: MemberObject? = null
    private var viewReference = WeakReference(view)

    override fun getView(): BaseIssueReferralContract.View? {
        return viewReference.get()
    }

    override fun <T> getViewModel(): Class<T> where T : ViewModel, T : BaseIssueReferralContract.Model {
        return viewModelClass as Class<T>
    }

    override fun getMainCondition() =
            "${Constants.Tables.FAMILY_MEMBER}.${DBConstants.Key.BASE_ENTITY_ID}  = '$baseEntityID'"

    override fun getMainTable() = Constants.Tables.FAMILY_MEMBER

    override fun fillClientData(memberObject: MemberObject?) {
        if (getView() != null) {
            getView()?.setProfileViewWithData()
        }
    }

    override fun initializeMemberObject(memberObject: MemberObject?) {
        this.memberObject = memberObject
    }

    override fun saveForm(valuesHashMap: HashMap<String, NFormViewData>, jsonObject: JSONObject, isAddoLinkage: Boolean, isKituoniLinkage: Boolean) {
        try {
            interactor.saveRegistration(baseEntityID, valuesHashMap, jsonObject, this, isAddoLinkage,isKituoniLinkage)
        } catch (e: JSONException) {
            Timber.e(Log.getStackTraceString(e))
        } catch (e: SQLiteException) {
            Timber.e(Log.getStackTraceString(e))
        }
    }

    override fun onUniqueIdFetched(triple: Triple<String, String, String>, entityId: String) = Unit

    override fun onNoUniqueId() = Unit

    override fun onRegistrationSaved(saveSuccessful: Boolean, isAddoLinkage: Boolean, isKituoniLinkage: Boolean) {
        val context = getView() as Activity
        val messageId = if (isAddoLinkage) {
            if (saveSuccessful) R.string.linkage_submitted else R.string.linkage_not_submitted
        }
       else if (isKituoniLinkage) {
            if (saveSuccessful) R.string.kituoni_linkage_submitted_successfully else R.string.kituoni_linkage_not_submitted
        }
        else {
            if (saveSuccessful) R.string.referral_submitted else R.string.referral_not_submitted
        }
        Utils.showToast(context, context.getString(messageId))
    }
}