package io.github.thibaultbee.streampack.example.app.utils

import android.content.Context
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.os.Build
import io.github.thibaultbee.streampack.example.R


/**
 * Get a string representation of a profile level.
 *
 * @param context The application context.
 */
class ProfileLevelDisplay(private val context: Context) {
    private val aacProfileNameMap =
        mutableMapOf(
            MediaCodecInfo.CodecProfileLevel.AACObjectMain to context.getString(R.string.audio_profile_main),
            MediaCodecInfo.CodecProfileLevel.AACObjectLC to context.getString(R.string.audio_profile_lc),
            MediaCodecInfo.CodecProfileLevel.AACObjectSSR to context.getString(R.string.audio_profile_ssr),
            MediaCodecInfo.CodecProfileLevel.AACObjectLTP to context.getString(R.string.audio_profile_ltp),
            MediaCodecInfo.CodecProfileLevel.AACObjectHE to context.getString(R.string.audio_profile_he),
            MediaCodecInfo.CodecProfileLevel.AACObjectScalable to context.getString(R.string.audio_profile_scalable),
            MediaCodecInfo.CodecProfileLevel.AACObjectERLC to context.getString(R.string.audio_profile_er_lc),
            MediaCodecInfo.CodecProfileLevel.AACObjectLD to context.getString(R.string.audio_profile_ld),
            MediaCodecInfo.CodecProfileLevel.AACObjectHE_PS to context.getString(R.string.audio_profile_he_ps),
            MediaCodecInfo.CodecProfileLevel.AACObjectELD to context.getString(R.string.audio_profile_eld)
        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                putAll(
                    mapOf(
                        MediaCodecInfo.CodecProfileLevel.AACObjectERScalable to context.getString(R.string.audio_profile_er_scalable)
                    )
                )
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                putAll(
                    mapOf(
                        MediaCodecInfo.CodecProfileLevel.AACObjectXHE to context.getString(R.string.audio_profile_xhe)
                    )
                )
            }
        }

    private val avcProfileNameMap =
        mutableMapOf(
            MediaCodecInfo.CodecProfileLevel.AVCProfileBaseline to context.getString(R.string.video_profile_baseline),
            MediaCodecInfo.CodecProfileLevel.AVCProfileExtended to context.getString(R.string.video_profile_extended),
            MediaCodecInfo.CodecProfileLevel.AVCProfileMain to context.getString(R.string.video_profile_main),
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh to context.getString(R.string.video_profile_high),
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh10 to context.getString(R.string.video_profile_high10),
            MediaCodecInfo.CodecProfileLevel.AVCProfileHigh422 to context.getString(R.string.video_profile_high422)
        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                putAll(
                    mapOf(
                        MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedBaseline to context.getString(R.string.video_profile_constrained_baseline),
                        MediaCodecInfo.CodecProfileLevel.AVCProfileConstrainedHigh to context.getString(R.string.video_profile_constrained_high)
                    )
                )
            }
        }

    private val hevcProfileNameMap =
        mutableMapOf(
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain to context.getString(R.string.video_profile_main),
            MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10 to context.getString(R.string.video_profile_main10)
        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                put(MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10, context.getString(R.string.video_profile_main10_hdr10))
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                put(MediaCodecInfo.CodecProfileLevel.HEVCProfileMainStill, context.getString(R.string.video_profile_main_still))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(
                    MediaCodecInfo.CodecProfileLevel.HEVCProfileMain10HDR10Plus,
                    context.getString(R.string.video_profile_main10_hdr10_plus)
                )
            }
        }

    private val vp9ProfileNameMap =
        mutableMapOf<Int, String>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                put(MediaCodecInfo.CodecProfileLevel.VP9Profile0, context.getString(R.string.video_profile_vp9_profile0))
                put(MediaCodecInfo.CodecProfileLevel.VP9Profile1, context.getString(R.string.video_profile_vp9_profile1))
                put(MediaCodecInfo.CodecProfileLevel.VP9Profile2, context.getString(R.string.video_profile_vp9_profile2))
                put(MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR, context.getString(R.string.video_profile_vp9_profile2_hdr10))
                put(
                    MediaCodecInfo.CodecProfileLevel.VP9Profile2HDR10Plus,
                    context.getString(R.string.video_profile_vp9_profile2_hrd10plus)
                )
                put(MediaCodecInfo.CodecProfileLevel.VP9Profile3, context.getString(R.string.video_profile_vp9_profile3))
                put(MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR, context.getString(R.string.video_profile_vp9_profile3_hdr10))
                put(
                    MediaCodecInfo.CodecProfileLevel.VP9Profile3HDR10Plus,
                    context.getString(R.string.video_profile_vp9_profile3_hdr10plus)
                )
            }
        }

    private val av1ProfileNameMap =
        mutableMapOf<Int, String>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain8, context.getString(R.string.video_profile_main))
                put(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10, context.getString(R.string.video_profile_main10))
                put(MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10, context.getString(R.string.video_profile_main10_hdr10))
                put(
                    MediaCodecInfo.CodecProfileLevel.AV1ProfileMain10HDR10Plus,
                    context.getString(R.string.video_profile_main10_hdr10_plus)
                )
            }
        }

    private val avcLevelNameMap =
        mutableMapOf(
            MediaCodecInfo.CodecProfileLevel.AVCLevel1 to context.getString(R.string.video_level_1),
            MediaCodecInfo.CodecProfileLevel.AVCLevel1b to context.getString(R.string.video_level_1b),
            MediaCodecInfo.CodecProfileLevel.AVCLevel11 to context.getString(R.string.video_level_11),
            MediaCodecInfo.CodecProfileLevel.AVCLevel12 to context.getString(R.string.video_level_12),
            MediaCodecInfo.CodecProfileLevel.AVCLevel13 to context.getString(R.string.video_level_13),
            MediaCodecInfo.CodecProfileLevel.AVCLevel2 to context.getString(R.string.video_level_2),
            MediaCodecInfo.CodecProfileLevel.AVCLevel21 to context.getString(R.string.video_level_21),
            MediaCodecInfo.CodecProfileLevel.AVCLevel22 to context.getString(R.string.video_level_22),
            MediaCodecInfo.CodecProfileLevel.AVCLevel3 to context.getString(R.string.video_level_3),
            MediaCodecInfo.CodecProfileLevel.AVCLevel31 to context.getString(R.string.video_level_31),
            MediaCodecInfo.CodecProfileLevel.AVCLevel32 to context.getString(R.string.video_level_32),
            MediaCodecInfo.CodecProfileLevel.AVCLevel4 to context.getString(R.string.video_level_4),
            MediaCodecInfo.CodecProfileLevel.AVCLevel41 to context.getString(R.string.video_level_41),
            MediaCodecInfo.CodecProfileLevel.AVCLevel42 to context.getString(R.string.video_level_42),
            MediaCodecInfo.CodecProfileLevel.AVCLevel5 to context.getString(R.string.video_level_5),
            MediaCodecInfo.CodecProfileLevel.AVCLevel51 to context.getString(R.string.video_level_51),
            MediaCodecInfo.CodecProfileLevel.AVCLevel52 to context.getString(R.string.video_level_52),
        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                putAll(
                    mapOf(
                        MediaCodecInfo.CodecProfileLevel.AVCLevel6 to context.getString(R.string.video_level_6),
                        MediaCodecInfo.CodecProfileLevel.AVCLevel61 to context.getString(R.string.video_level_61),
                        MediaCodecInfo.CodecProfileLevel.AVCLevel62 to context.getString(R.string.video_level_62)
                    )
                )
            }
        }

    private val hevcLevelNameMap =
        mutableMapOf(
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel1 to context.getString(R.string.video_level_main_level1),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel1 to context.getString(R.string.video_level_high_level1),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel2 to context.getString(R.string.video_level_main_level2),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel2 to context.getString(R.string.video_level_high_level2),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel21 to context.getString(R.string.video_level_main_level21),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel21 to context.getString(R.string.video_level_high_level21),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel3 to context.getString(R.string.video_level_main_level3),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel3 to context.getString(R.string.video_level_high_level3),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel31 to context.getString(R.string.video_level_main_level31),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel31 to context.getString(R.string.video_level_high_level31),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel4 to context.getString(R.string.video_level_main_level4),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel4 to context.getString(R.string.video_level_high_level4),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel41 to context.getString(R.string.video_level_main_level41),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel41 to context.getString(R.string.video_level_high_level41),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel5 to context.getString(R.string.video_level_main_level5),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel5 to context.getString(R.string.video_level_high_level5),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel51 to context.getString(R.string.video_level_main_level51),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel51 to context.getString(R.string.video_level_high_level51),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel52 to context.getString(R.string.video_level_main_level52),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel52 to context.getString(R.string.video_level_high_level52),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel6 to context.getString(R.string.video_level_main_level6),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel6 to context.getString(R.string.video_level_high_level6),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel61 to context.getString(R.string.video_level_main_level61),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel61 to context.getString(R.string.video_level_high_level61),
            MediaCodecInfo.CodecProfileLevel.HEVCMainTierLevel62 to context.getString(R.string.video_level_main_level62),
            MediaCodecInfo.CodecProfileLevel.HEVCHighTierLevel62 to context.getString(R.string.video_level_high_level62),
        )

    private val vp9LevelNameMap =
        mutableMapOf<Int, String>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                putAll(
                    mapOf(
                        MediaCodecInfo.CodecProfileLevel.VP9Level1 to context.getString(R.string.video_level_1),
                        MediaCodecInfo.CodecProfileLevel.VP9Level11 to context.getString(R.string.video_level_11),
                        MediaCodecInfo.CodecProfileLevel.VP9Level2 to context.getString(R.string.video_level_2),
                        MediaCodecInfo.CodecProfileLevel.VP9Level21 to context.getString(R.string.video_level_21),
                        MediaCodecInfo.CodecProfileLevel.VP9Level3 to context.getString(R.string.video_level_3),
                        MediaCodecInfo.CodecProfileLevel.VP9Level31 to context.getString(R.string.video_level_31),
                        MediaCodecInfo.CodecProfileLevel.VP9Level4 to context.getString(R.string.video_level_4),
                        MediaCodecInfo.CodecProfileLevel.VP9Level41 to context.getString(R.string.video_level_41),
                        MediaCodecInfo.CodecProfileLevel.VP9Level5 to context.getString(R.string.video_level_5),
                        MediaCodecInfo.CodecProfileLevel.VP9Level51 to context.getString(R.string.video_level_51),
                        MediaCodecInfo.CodecProfileLevel.VP9Level52 to context.getString(R.string.video_level_52),
                        MediaCodecInfo.CodecProfileLevel.VP9Level6 to context.getString(R.string.video_level_6),
                        MediaCodecInfo.CodecProfileLevel.VP9Level61 to context.getString(R.string.video_level_61),
                        MediaCodecInfo.CodecProfileLevel.VP9Level62 to context.getString(R.string.video_level_62),
                    )
                )
            }
        }

    private val av1LevelNameMap =
        mutableMapOf<Int, String>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                putAll(
                    mapOf(
                        MediaCodecInfo.CodecProfileLevel.AV1Level2 to context.getString(R.string.video_level_2),
                        MediaCodecInfo.CodecProfileLevel.AV1Level21 to context.getString(R.string.video_level_21),
                        MediaCodecInfo.CodecProfileLevel.AV1Level22 to context.getString(R.string.video_level_22),
                        MediaCodecInfo.CodecProfileLevel.AV1Level23 to context.getString(R.string.video_level_21),
                        MediaCodecInfo.CodecProfileLevel.AV1Level3 to context.getString(R.string.video_level_3),
                        MediaCodecInfo.CodecProfileLevel.AV1Level31 to context.getString(R.string.video_level_31),
                        MediaCodecInfo.CodecProfileLevel.AV1Level32 to context.getString(R.string.video_level_32),
                        MediaCodecInfo.CodecProfileLevel.AV1Level33 to context.getString(R.string.video_level_33),
                        MediaCodecInfo.CodecProfileLevel.AV1Level4 to context.getString(R.string.video_level_4),
                        MediaCodecInfo.CodecProfileLevel.AV1Level41 to context.getString(R.string.video_level_41),
                        MediaCodecInfo.CodecProfileLevel.AV1Level42 to context.getString(R.string.video_level_42),
                        MediaCodecInfo.CodecProfileLevel.AV1Level43 to context.getString(R.string.video_level_43),
                        MediaCodecInfo.CodecProfileLevel.AV1Level5 to context.getString(R.string.video_level_5),
                        MediaCodecInfo.CodecProfileLevel.AV1Level51 to context.getString(R.string.video_level_51),
                        MediaCodecInfo.CodecProfileLevel.AV1Level52 to context.getString(R.string.video_level_52),
                        MediaCodecInfo.CodecProfileLevel.AV1Level53 to context.getString(R.string.video_level_53),
                        MediaCodecInfo.CodecProfileLevel.AV1Level6 to context.getString(R.string.video_level_6),
                        MediaCodecInfo.CodecProfileLevel.AV1Level61 to context.getString(R.string.video_level_61),
                        MediaCodecInfo.CodecProfileLevel.AV1Level62 to context.getString(R.string.video_level_62),
                        MediaCodecInfo.CodecProfileLevel.AV1Level63 to context.getString(R.string.video_level_63),
                        MediaCodecInfo.CodecProfileLevel.AV1Level7 to context.getString(R.string.video_level_7),
                        MediaCodecInfo.CodecProfileLevel.AV1Level71 to context.getString(R.string.video_level_71),
                        MediaCodecInfo.CodecProfileLevel.AV1Level72 to context.getString(R.string.video_level_72),
                        MediaCodecInfo.CodecProfileLevel.AV1Level73 to context.getString(R.string.video_level_73),
                    )
                )
            }
        }

    private fun getProfileMap(mimeType: String) = when (mimeType) {
        MediaFormat.MIMETYPE_AUDIO_AAC -> aacProfileNameMap
        MediaFormat.MIMETYPE_VIDEO_AVC -> avcProfileNameMap
        MediaFormat.MIMETYPE_VIDEO_HEVC -> hevcProfileNameMap
        MediaFormat.MIMETYPE_VIDEO_VP9 -> vp9ProfileNameMap
        MediaFormat.MIMETYPE_VIDEO_AV1 -> av1ProfileNameMap
        else -> emptyMap()
    }

    fun getProfileName(mimeType: String, profile: Int): String {
        val nameMap = getProfileMap(mimeType)
        return try {
            nameMap[profile]!!
        } catch (_: Exception) {
            context.getString(R.string.av_profile_unknown)
        }
    }

    fun getProfile(mimeType: String, name: String): Int {
        val nameMap = getProfileMap(mimeType)
        return nameMap.entries.first { it.value == name }.key
    }

    private fun getLevelMap(mimeType: String) = when (mimeType) {
        MediaFormat.MIMETYPE_VIDEO_AVC -> avcLevelNameMap
        MediaFormat.MIMETYPE_VIDEO_HEVC -> hevcLevelNameMap
        MediaFormat.MIMETYPE_VIDEO_VP9 -> vp9LevelNameMap
        MediaFormat.MIMETYPE_VIDEO_AV1 -> av1LevelNameMap
        else -> emptyMap()
    }

    fun getLevelName(mimeType: String, level: Int): String {
        val nameMap = getLevelMap(mimeType)
        return try {
            nameMap[level]!!
        } catch (_: Exception) {
            context.getString(R.string.av_level_unknown)
        }
    }

    fun getLevel(mimeType: String, name: String): Int {
        val nameMap = getLevelMap(mimeType)
        return nameMap.entries.first { it.value == name }.key
    }

    fun getAllLevelSet(mimeType: String): Set<Int> {
        return getLevelMap(mimeType).keys
    }
}