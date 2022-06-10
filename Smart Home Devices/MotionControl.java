public interface MotionControl {

    default boolean motionControl(boolean hasMotion, boolean isDay) {
        return false;
    }

}
