package io.github.thibaultbee.streampack.example.app.utils

import io.github.thibaultbee.streampack.example.app.models.EndpointType
import io.github.thibaultbee.streampack.streamers.helpers.CameraStreamerConfigurationHelper

class StreamerHelperFactory(
    private val endpointType: EndpointType,
) {
    fun build(): CameraStreamerConfigurationHelper {
        return when (endpointType) {
            EndpointType.TS_FILE -> CameraStreamerConfigurationHelper.tsHelper
            EndpointType.FLV_FILE -> CameraStreamerConfigurationHelper.flvHelper
            EndpointType.SRT -> CameraStreamerConfigurationHelper.tsHelper
            EndpointType.RTMP -> CameraStreamerConfigurationHelper.flvHelper
            EndpointType.MP4_FILE -> CameraStreamerConfigurationHelper.mp4Helper
        }
    }
}