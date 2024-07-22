package io.github.thibaultbee.streampack.example.app.models

sealed class Endpoint(
    val hasTSCapabilities: Boolean,
    val hasFLVCapabilities: Boolean,
    val hasMP4Capabilities: Boolean,
    val hasFileCapabilities: Boolean,
    val hasSrtCapabilities: Boolean,
    val hasRtmpCapabilities: Boolean
) {
    class TsFileEndpoint : Endpoint(true, false, false, true, false, false)
    class FlvFileEndpoint : Endpoint(false, true, false, true, false, false)
    class Mp4FileEndpoint : Endpoint(false, false, true, true, false, false)
    class SrtEndpoint : Endpoint(true, false, false, false, true, false)
    class RtmpEndpoint : Endpoint(false, true, false, false, false, true)
}

class EndpointFactory(private val type: EndpointType) {
    fun build(): Endpoint {
        return when (type) {
            EndpointType.TS_FILE -> Endpoint.TsFileEndpoint()
            EndpointType.FLV_FILE -> Endpoint.FlvFileEndpoint()
            EndpointType.SRT -> Endpoint.SrtEndpoint()
            EndpointType.RTMP -> Endpoint.RtmpEndpoint()
            EndpointType.MP4_FILE -> Endpoint.Mp4FileEndpoint()
        }
    }
}