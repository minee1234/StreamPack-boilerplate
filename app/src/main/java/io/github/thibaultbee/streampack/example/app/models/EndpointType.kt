package io.github.thibaultbee.streampack.example.app.models

enum class EndpointType(val id: Int) {
    TS_FILE(0),
    FLV_FILE(1),
    SRT(2),
    RTMP(3),
    MP4_FILE(4);

    companion object {
        fun fromId(id: Int): EndpointType = entries.first { it.id == id }
    }
}
