package org.smartregister.chw.referral.provider

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nerdstone.neatformcore.utils.isNotNull
import org.joda.time.DateTime
import org.joda.time.Period
import org.smartregister.chw.referral.R
import org.smartregister.chw.referral.fragment.BaseReferralRegisterFragment
import org.smartregister.chw.referral.util.Constants
import org.smartregister.chw.referral.util.DBConstants
import org.smartregister.chw.referral.util.ReferralUtil
import org.smartregister.chw.referral.util.Util
import org.smartregister.commonregistry.CommonPersonObjectClient
import org.smartregister.cursoradapter.RecyclerViewProvider
import org.smartregister.domain.Task
import org.smartregister.util.Utils
import org.smartregister.view.contract.SmartRegisterClient
import org.smartregister.view.dialog.FilterOption
import org.smartregister.view.dialog.ServiceModeOption
import org.smartregister.view.dialog.SortOption
import timber.log.Timber
import java.text.MessageFormat
import java.util.*
import org.smartregister.configurableviews.model.View as ConfigurableView

open class ReferralRegisterProvider(
        private val context: Context, private val paginationClickListener: View.OnClickListener,
        private var onClickListener: View.OnClickListener,
        private val visibleColumns: Set<ConfigurableView>?
) : RecyclerViewProvider<ReferralRegisterProvider.RegisterViewHolder> {

    private val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(
            cursor: Cursor, smartRegisterClient: SmartRegisterClient,
            registerViewHolder: RegisterViewHolder
    ) {
        if (visibleColumns!!.isEmpty())
            populatePatientColumn(
                    smartRegisterClient as CommonPersonObjectClient, registerViewHolder
            )
    }

    override fun getFooterView(
            viewHolder: RecyclerView.ViewHolder, currentPageCount: Int, totalPageCount: Int,
            hasNext: Boolean, hasPrevious: Boolean
    ) {
        (viewHolder as FooterViewHolder)
                .apply {
                    pageInfoView.text = MessageFormat.format(
                            context.getString(org.smartregister.R.string.str_page_info),
                            currentPageCount,
                            totalPageCount
                    )
                    nextPageView.visibility = if (hasNext) View.VISIBLE else View.INVISIBLE
                    nextPageView.setOnClickListener(paginationClickListener)
                    previousPageView.visibility = if (hasPrevious) View.VISIBLE else View.INVISIBLE
                    previousPageView.setOnClickListener(paginationClickListener)
                }
    }

    override fun updateClients(
            villageFilter: FilterOption?, serviceModeOption: ServiceModeOption?,
            searchFilter: FilterOption?, sortOption: SortOption?
    ) = null

    override fun onServiceModeSelected(serviceModeOption: ServiceModeOption) = Unit

    override fun newFormLauncher(formName: String?, entityId: String?, metaData: String?) = null

    override fun inflater() = inflater

    override fun createViewHolder(parent: ViewGroup) = RegisterViewHolder(
            inflater.inflate(R.layout.referral_register_list_row_item, parent, false)
    )

    override fun createFooterHolder(parent: ViewGroup) = FooterViewHolder(
            inflater.inflate(R.layout.smart_register_pagination, parent, false)
    )

    override fun isFooterViewHolder(viewHolder: RecyclerView.ViewHolder) =
            viewHolder is FooterViewHolder

    private fun populatePatientColumn(
            pc: CommonPersonObjectClient, viewHolder: RegisterViewHolder
    ) {
        try {
            val firstName = Utils.getName(
                    Utils.getValue(pc.columnmaps, DBConstants.Key.FIRST_NAME, true),
                    Utils.getValue(pc.columnmaps, DBConstants.Key.MIDDLE_NAME, true)
            )
            val dobString = Utils.getValue(pc.columnmaps, DBConstants.Key.DOB, false)
            val age = Period(DateTime(dobString), DateTime()).years
            val patientName = Utils.getName(
                    firstName, Utils.getValue(pc.columnmaps, DBConstants.Key.LAST_NAME, true)
            )
            val baseEntityId = Utils.getValue(pc.columnmaps, DBConstants.Key.BASE_ENTITY_ID, false)

            with(viewHolder) {
                this.patientName.text = String.format(
                        Locale.getDefault(), "%s, %d", patientName, age
                )
                textViewGender.text = ReferralUtil.getTranslatedGenderString(context,
                        Utils.getValue(pc.columnmaps, DBConstants.Key.GENDER, true))
                textViewVillage.text =
                        Utils.getValue(pc.columnmaps, DBConstants.Key.VILLAGE_TOWN, true)

                val referralType = Utils.getValue(
                        pc.columnmaps, DBConstants.Key.REFERRAL_SERVICE, true
                )
                textViewService.text = ReferralUtil.getTranslatedReferralServiceType(context, referralType)
                textViewFacility.text = Utils.getValue(
                        pc.columnmaps, DBConstants.Key.REFERRAL_HF, true
                )

                patientColumn.apply {
                    setOnClickListener(onClickListener)
                    tag = pc
                    setTag(R.id.VIEW_ID, BaseReferralRegisterFragment.CLICK_VIEW_NORMAL)
                }
                textReferralStatus.apply {
                    setOnClickListener(onClickListener)
                    tag = pc
                    setTag(R.id.VIEW_ID, BaseReferralRegisterFragment.FOLLOW_UP_VISIT)
                }
                registerColumns.setOnClickListener(onClickListener)
                registerColumns.setOnClickListener { patientColumn.performClick() }

                dueWrapper.setOnClickListener(onClickListener)

                setReferralStatusColor(
                        context, textReferralStatus,
                        Utils.getValue(pc.columnmaps, DBConstants.Key.REFERRAL_STATUS, true)
                )

                val taskId = Utils.getValue(pc.columnmaps, Constants.Task.Key.TASK_ID, false);

                val task = Util.getFollowUpTask(taskId)

                task?.let {
                    if (it.status == Task.TaskStatus.READY){
                        //Follow up task is available and not followed up on
                        followUpWrapper.apply {
                            visibility = View.VISIBLE
                            setOnClickListener(onClickListener)
                            tag = pc
                            setTag(R.id.VIEW_ID, BaseReferralRegisterFragment.LINKAGE_FOLLOWUP)
                            setTag(R.id.FOLLOW_UP_TASK, task )
                        }
                    }else{
                        //Follow up task is already followed up on
                        followUpWrapper.visibility = View.INVISIBLE
                        val statusValue = Utils.getValue(pc.columnmaps, DBConstants.Key.STATUS, true)
                        setReferralStatusColor( context, textReferralStatus, statusValue)
                    }
                }
                task?:let {
                    //Follow up task is not available
                    followUpWrapper.visibility = View.INVISIBLE
                    val statusValue = Utils.getValue(pc.columnmaps, DBConstants.Key.STATUS, true)
                    setReferralStatusColor(context, textReferralStatus, statusValue)
                }
            }
        } catch (e: IllegalStateException) {
            Timber.e(e)
        }
    }

    private fun setReferralStatusColor(context: Context, textViewStatus: TextView, status: String) {
        when (status) {
            Constants.BusinessStatus.REFERRED -> {
                textViewStatus.setTextColor(
                        ContextCompat.getColor(context, R.color.alert_in_progress_blue)
                )
                textViewStatus.text = context.getString(R.string.referral_status_pending)
            }
            Constants.BusinessStatus.EXPIRED -> {
                textViewStatus.setTextColor(
                        ContextCompat.getColor(context, R.color.alert_urgent_red)
                )
                textViewStatus.text = context.getString(R.string.referral_status_failed)
            }
            Constants.Task.Status.COMPLETED -> {
                textViewStatus.setTextColor(
                        ContextCompat.getColor(context, R.color.alert_complete_green)
                )
                textViewStatus.text = context.getString(R.string.referral_status_successful)
            }
        }
    }

    open inner class RegisterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var patientName: TextView = itemView.findViewById(R.id.patient_name_age)
        var textViewVillage: TextView = itemView.findViewById(R.id.text_view_village)
        var textViewGender: TextView = itemView.findViewById(R.id.text_view_gender)
        var textReferralStatus: TextView = itemView.findViewById(R.id.text_view_referral_status)
        var patientColumn: View = itemView.findViewById(R.id.patient_column)
        var textViewService: TextView = itemView.findViewById(R.id.text_view_service)
        var textViewFacility: TextView = itemView.findViewById(R.id.text_view_facility)
        var registerColumns: View = itemView.findViewById(R.id.register_columns)
        var dueWrapper: View = itemView.findViewById(R.id.due_button_wrapper)
        var followUpWrapper: View = itemView.findViewById(R.id.followup_button_wrapper);
    }

    open inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pageInfoView: TextView = view.findViewById(org.smartregister.R.id.txt_page_info)
        var nextPageView: Button = view.findViewById(org.smartregister.R.id.btn_next_page)
        var previousPageView: Button = view.findViewById(org.smartregister.R.id.btn_previous_page)

    }

    companion object {
        protected var client: CommonPersonObjectClient? = null
    }
}