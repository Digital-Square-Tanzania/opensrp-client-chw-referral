package org.smartregister.chw.referral.util

/**
 * Contains constants used thought the application
 */
object Constants {
    const val EN = "en"
    const val SW = "sw"
    const val REQUEST_CODE_GET_JSON = 2244
    const val ENCOUNTER_TYPE = "encounter_type"
    const val STEP_ONE = "step1"
    const val STEP_TWO = "step2"

    /**
     * Configuration constants used thought the application
     */
    object Configuration {
        const val ISSUE_REFERRAL = "issue_referral"
    }

    /**
     * ReferralMemberObject constants used thought the application
     */
    object ReferralMemberObject {
        const val MEMBER_OBJECT = "memberObject"
        const val COMMON_PERSON_OBJECT = "commonPersonObjectClient"
    }

    /**
     * ReferralServiceType constants used thought the application
     */
    object ReferralServiceType {
        const val SICK_CHILD = "Sick Child"
        const val ANC_DANGER_SIGNS = "ANC Danger Signs"
        const val PNC_DANGER_SIGNS = "PNC Danger Signs"
        const val FP_SIDE_EFFECTS = "FP Initiation"
        const val SUSPECTED_MALARIA = "Suspected Malaria"
        const val SUSPECTED_HIV = "Suspected HIV"
        const val SUSPECTED_TB = "Suspected TB"
        const val SUSPECTED_GBV = "Suspected GBV"
        const val SUSPECTED_CHILD_GBV = "Suspected Child GBV"
        const val PREGNANCY_CONFIRMATION = "Pregnancy Confirmation"
        const val ANC_MALE_ENGAGEMENT = "ANC Male Engagement Referral"
        const val PMTCT_REFERRAL = "PMTCT Referral"
        const val KVP_FRIENDLY_SERVICES = "KVP Friendly Services"
        const val STI_REFERRAL = "STI Services"
        const val AYSRH_FRIENDLY_SERVICES = "AYSRH Friendly Services"
        const val CECAP_REFERRAL = "Reproductive Cancer Services";

        /**
         *  Linkage service constants for linking to ADDO
         */
        object ADDO {
            const val CHILD_MINOR_AILMENTS = "Child Minor Ailments"
            const val ANC_MINOR_AILMENTS = "ANC Minor Ailments"
            const val PNC_MINOR_AILMENTS = "ANC Minor Ailments"
            const val ADOLESCENT_MINOR_AILMENTS = "Adolescent Minor Ailments"
        }

    }

    /**
     * ReferralType constants used thought the application
     */
    object ReferralType {
        const val COMMUNITY_TO_FACILITY_REFERRAL = "community_to_facility_referral"
        const val FACILITY_TO_COMMUNITY_REFERRAL = "facility_to_community_referral"
        const val COMMUNITY_TO_ADDO_REFERRAL = "community_to_addo_referral"
    }

    /**
     * BusinessStatus constants used thought the application
     */
    object BusinessStatus {
        const val REFERRED = "Referred"
        const val IN_PROGRESS = "In-Progress"
        const val COMPLETE = "Complete"
        const val EXPIRED = "Expired"
        const val LINKED = "Linked"
    }


    /**
     * Task constants used thought the application
     */
    object Task {

        object Key {
            const val TASK_ID = "taskId"
            const val FOLLOW_UP_TASK = "follow_up_task"
        }

        /**
         * Task Status constants used thought the application
         */
        object Status {
            const val DRAFT = "Draft"
            const val READY = "Ready"
            const val CANCELLED = "Cancelled"
            const val IN_PROGRESS = "In-Progress"
            const val COMPLETED = "COMPLETED"
            const val FAILED = "FAILED"
            const val ARCHIVED = "ARCHIVED"
            const val EXPIRED = "EXPIRED"
        }
    }

    /**
     * Referral constants used thought the application
     */
    object Referral {
        const val PLAN_ID = "5270285b-5a3b-4647-b772-c0b3c52e2b71"
        const val CODE = "Referral"
    }

    /**
     *  ADDO Linkage (Task) constants
     */
    object Addo {
        const val PLAN_ID = "6270285b-5a3b-4647-b772-c0b3c52e2b72"
        const val CODE = "Linkage"
    }

    /**
     * JsonFormExtra constants used thought the application
     */
    object JsonFormExtra {
        const val JSON = "json"
        const val ENCOUNTER_TYPE = "encounter_type"
    }

    /**
     * EventType constants used thought the application
     */
    object EventType {
        const val REGISTRATION = "Referral Registration"
        const val REFERRAL_FOLLOW_UP_VISIT = "Followup Visit"
        const val LINKAGE_FOLLOW_UP_VISIT = "Linkage Followup Visit"
    }

    /**
     * Forms constants used thought the application
     */
    object Forms {
        const val REFERRAL_REGISTRATION = "general_referral_form"
        const val REFERRAL_FOLLOW_UP_VISIT = "referral_followup_visit"
        const val LINKAGE_FOLLOW_UP_VISIT = "linkage_followup_visit"
    }

    /**
     * Tables constants used thought the application
     */
    object Tables {
        const val REFERRAL = "ec_referral"
        const val REFERRAL_SERVICE = "ec_referral_service"
        const val REFERRA_SERVICE_INDICATOR = "ec_referral_service_indicator"
        const val REFERRAL_FOLLOW_UP = "ec_referral_follow_up_visit"
        const val FAMILY_MEMBER = "ec_family_member"
    }

    /**
     * ActivityPayload constants used thought the application
     */
    object ActivityPayload {
        const val BASE_ENTITY_ID = "BASE_ENTITY_ID"
        const val ACTION = "ACTION"
        const val REFERRAL_FORM_NAME = "REFERRAL_FORM_NAME"
        const val JSON_FORM = "JSON_FORM"
        const val REFERRAL_SERVICE_IDS = "REFERRAL_SERVICE_IDS"
        const val USE_CUSTOM_LAYOUT = "USE_CUSTOM_LAYOUT"
        const val IS_ADDO_LINKAGE = "IS_ADDO_LINKAGE"
    }

    /**
     * ActivityPayloadType constants used thought the application
     */
    object ActivityPayloadType {
        const val REGISTRATION = "REGISTRATION"
        const val FOLLOW_UP_VISIT = "FOLLOW_UP_VISIT"
    }
}

/**
 * DBConstants constants used thought the application
 */
object DBConstants {
    object Key {
        const val ID = "id"
        const val FIRST_NAME = "first_name"
        const val MIDDLE_NAME = "middle_name"
        const val LAST_NAME = "last_name"
        const val BASE_ENTITY_ID = "base_entity_id"
        const val FAMILY_BASE_ENTITY_ID = "family_base_entity_id"
        const val DOB = "dob"
        const val DOD = "dod"
        const val UNIQUE_ID = "unique_id"
        const val VILLAGE_TOWN = "village_town"
        const val DATE_REMOVED = "date_removed"
        const val GENDER = "gender"
        const val DETAILS = "details"
        const val RELATIONAL_ID = "relationalid"
        const val FAMILY_HEAD = "family_head"
        const val PRIMARY_CARE_GIVER = "primary_caregiver"
        const val FAMILY_NAME = "family_name"
        const val PHONE_NUMBER = "phone_number"
        const val OTHER_PHONE_NUMBER = "other_phone_number"
        const val FAMILY_HEAD_PHONE_NUMBER = "family_head_phone_number"
        const val REFERRAL_HF = "chw_referral_hf"
        const val REFERRAL_REASON = "chw_referral_reason"
        const val REFERRAL_SERVICE = "chw_referral_service"
        const val REFERRAL_SERVICE_ID = "chw_referral_service_id"
        const val REFERRAL_APPOINTMENT_DATE = "referral_appointment_date"
        const val REFERRAL_DATE = "referral_date"
        const val REFERRAL_TIME = "referral_time"
        const val IS_EMERGENCY_REFERRAL = "is_emergency_referral"
        const val PROBLEM = "problem"
        const val PROBLEM_OTHER = "problem_other"
        const val SERVICE_BEFORE_REFERRAL = "service_before_referral"
        const val SERVICE_BEFORE_REFERRAL_OTHER = "service_before_referral_other"
        const val REFERRAL_TYPE = "referral_type"
        const val REFERRAL_STATUS = "referral_status"
        const val STATUS = "status"
        const val NAME_EN = "name_en"
        const val NAME_SW = "name_sw"
        const val REFERRAL_SERVICE_IDENTIFIER = "identifier"
        const val IS_ACTIVE = "isActive"
        const val IS_CLOSED = "is_closed"
        const val OTHER_FOLLOWUP_FEEDBACK_INFORMATION =
            "other_followup_feedback_information"
        const val CHW_FOLLOWUP_FEEDBACK_ID = "chw_followup_feedback_id"
        const val CHW_FOLLOWUP_DATE = "chw_followup_date"
    }
}


