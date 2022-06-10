public class SmartCamera extends SmartObject implements MotionControl, Comparable {

    private boolean status;
    private int batteryLife;
    private boolean nightVision;


    public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
        super.setAlias(alias);
        super.setMacId(macId);
        this.setNightVision(nightVision);
        this.setBatteryLife(batteryLife);
    }




    @Override
    public boolean controlConnection() {
        if (!this.isConnectionStatus()) {
            System.out.println("This device is not connected. SmartCamera -> " + this.getAlias());
            return false;
        } else {

            return true;
        }
    }




    @Override
    public void SmartObjectToString() {

        System.out.println("This is SmartCamera device " + this.getAlias());
        System.out.println("    MacId: " + this.getMacId());
        System.out.println("    IP: " + this.getIP());

    }




    public void recordOn(boolean isDay) {

        if (this.controlConnection()) {

            if (!isDay && !this.nightVision && !this.status) {
                System.out.println("Sorry! Smart Camera - " + this.getAlias() + " does not have night vision feature.");
            } else if (!this.status) {
                System.out.println("Smart Camera - " + this.getAlias() + " is turned on now");
                this.status = true;
            } else {
                System.out.println("Smart Camera - " + this.getAlias() + " has been already turned on");
            }
        }
    }




    public void recordOff() {
        if (this.controlConnection()) {
            if (this.status) {
                System.out.println("Smart Camera - " + this.getAlias() + " is turned off now");
                this.status = false;
            } else {
                System.out.println("Smart Camera - " + this.getAlias() + " has been already turned off");
            }
        }
    }




    public boolean testObject() {

        if (this.controlConnection()) {

            this.SmartObjectToString();
            System.out.println("Test is starting for SmartCamera day time");
            this.recordOn(true);
            this.recordOff();
            System.out.println("Test is starting for SmartCamera night time");
            this.recordOn(false);
            recordOff();
            System.out.println("Test completed for SmartCamera");
            return true;
        } else {
            return false;
        }

    }




    public boolean shutDownObject() {

        if (this.controlConnection()) {

            if (this.status) {
                this.SmartObjectToString();
                this.status = false;
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }





    public boolean controlMotion(boolean hasMotion, boolean isDay) {

        if (hasMotion) {

            System.out.println("Motion detected!");

            if (isDay) {
                this.recordOn(true);
                return true;
            } else {
                if (this.nightVision) {
                    this.recordOn(false);
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            System.out.println("Motion not detected!");
            return false;
        }

    }






    public int compareTo(SmartCamera smartCamera) {

        if (this.batteryLife > smartCamera.getBatteryLife()) {
            return 1;
        } else if (this.batteryLife == smartCamera.getBatteryLife()) {
            return 0;
        } else {
            return -1;
        }
    }





    @Override
    public String toString() {

        if (this.status) {
            return "SmartCamera -> " + this.getAlias() + "'s  battery life is " + this.getBatteryLife() + " status is recording";
        } else {
            return "SmartCamera -> " + this.getAlias() + "'s  battery life is " + this.getBatteryLife() + " status is not recording";
        }
    }





    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }
}
