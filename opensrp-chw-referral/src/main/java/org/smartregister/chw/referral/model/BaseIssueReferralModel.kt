package org.smartregister.chw.referral.model

import android.database.sqlite.SQLiteException
import org.smartregister.chw.referral.util.DBConstants
import org.smartregister.cursoradapter.SmartRegisterQueryBuilder
import org.smartregister.domain.Location
import org.smartregister.repository.LocationRepository
import org.smartregister.repository.LocationTagRepository
import timber.log.Timber

open class BaseIssueReferralModel : AbstractIssueReferralModel() {

    override fun getLocationId(locationName: String?): String? = null

    override val healthFacilities: List<Location>?
        get() = try {
            LocationRepository().allLocations.filter { location ->
                LocationTagRepository().getLocationTagByLocationId(location.id).any { it.name.equals("Facility", ignoreCase = true) }
            }
        } catch (e: SQLiteException) {
            Timber.e(e)
            null
        }


    override fun mainSelect(tableName: String, mainCondition: String): String {
        val queryBuilder = SmartRegisterQueryBuilder()
        queryBuilder.selectInitiateMainTable(tableName, mainColumns(tableName))
        return queryBuilder.mainCondition(mainCondition)
    }

    open fun mainColumns(tableName: String) = arrayOf(
        tableName + "." + DBConstants.Key.RELATIONAL_ID,
        tableName + "." + DBConstants.Key.BASE_ENTITY_ID,
        tableName + "." + DBConstants.Key.FIRST_NAME,
        tableName + "." + DBConstants.Key.MIDDLE_NAME,
        tableName + "." + DBConstants.Key.LAST_NAME,
        tableName + "." + DBConstants.Key.UNIQUE_ID,
        tableName + "." + DBConstants.Key.GENDER,
        tableName + "." + DBConstants.Key.DOB,
        tableName + "." + DBConstants.Key.DOD
    )

}