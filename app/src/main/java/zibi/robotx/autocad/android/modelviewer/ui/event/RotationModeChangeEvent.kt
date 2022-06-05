package zibi.robotx.autocad.android.modelviewer.ui.event

import zibi.robotx.autocad.android.common.event.IEvent

class RotationModeChangeEvent(val mode: RotationMode) : IEvent {

    enum class RotationMode {
        XY, Free, Block
    }

}