import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmartLight extends SmartObject implements LocationControl, Programmable {

    private boolean hasLightTurned;
    private Calendar programTime;
    private boolean programAction;




    public SmartLight(String alias, String macId) {
        super.setAlias(alias);
        super.setMacId(macId);
    }





    @Override
    public boolean controlConnection() {
        if (!this.isConnectionStatus()) {
            System.out.println("This device is not connected. SmartLight -> " + this.getAlias());
            return false;
        } else {

            return true;
        }
    }





    @Override
    public void SmartObjectToString() {

        System.out.println("This is SmartLight device " + this.getAlias());
        System.out.println("    MacId: " + this.getMacId());
        System.out.println("    IP: " + this.getIP());

    }






    public void turnOnLight() {

        if (this.controlConnection()) {
            if (!this.hasLightTurned) {
                this.setHasLightTurned(true);

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                System.out.println("Smart Light - " + this.getAlias() + " is turned on now (Current Time: " + strDate + ")");
            } else {
                System.out.println("Smart Light - " + this.getAlias() + " has been already turned on");
            }
        }
    }





    public void turnOffLight() {

        if (this.controlConnection()) {
            if (this.hasLightTurned) {
                this.setHasLightTurned(false);

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                System.out.println("Smart Light - " + this.getAlias() + " is turned off now (Current Time: " + strDate + ")");
            } else {
                System.out.println("Smart Light - " + this.getAlias() + "has been already turned off");
            }
        }
    }





    @Override
    public boolean testObject() {

        if (this.controlConnection()) {

            this.SmartObjectToString();
            this.turnOnLight();
            this.turnOffLight();
            System.out.println("Test completed for SmartLight");
            return true;
        } else {
            return false;
        }
    }





    @Override
    public boolean shutDownObject() {
        if (this.controlConnection()) {
            if (this.hasLightTurned) {
                this.SmartObjectToString();
                this.turnOffLight();
            }
            return true;
        } else {
            return false;
        }
    }






    public void onLeave() {

        if (this.controlConnection()) {
            System.out.println("On Leave -> Smart Light - " + this.getAlias());
            this.turnOffLight();
        }
    }






    public void onCome() {

        if (this.controlConnection()) {
            System.out.println("On Come -> Smart Light - " + this.getAlias());
            this.turnOnLight();
        }
    }





    public void setTimer(int seconds) {

        if (this.controlConnection()) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, seconds);

            this.setProgramTime(calendar);

            if (this.hasLightTurned) {
                System.out.println("Smart light - " + this.getAlias() + " will be turned off " + seconds + " seconds later!");

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                System.out.println("(Current time: " + strDate + ")");
            } else {
                System.out.println("Smart light - " + this.getAlias() + " will be turned on " + seconds + " seconds later!");

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                System.out.println("(Current time: " + strDate + ")");
            }

        }
    }







    public void cancelTimer() {

        if (this.controlConnection()) {
            this.setProgramTime(null);
        }
    }

    public void runProgram() {

        if (this.controlConnection()) {

            if (this.getProgramTime() != null) {
                if ((this.getProgramTime().compareTo(Calendar.getInstance()) == 0) && this.hasLightTurned) {
                    System.out.println("RunProgram -> Smart Light - " + this.getAlias());
                    this.turnOffLight();
                } else if ((this.getProgramTime().compareTo(Calendar.getInstance()) == 0) && !this.hasLightTurned) {
                    System.out.println("RunProgram -> Smart Light - " + this.getAlias());
                    this.turnOnLight();
                }
            }


        }
    }






    public boolean isHasLightTurned() {
        return hasLightTurned;
    }


    public void setHasLightTurned(boolean hasLightTurned) {
        this.hasLightTurned = hasLightTurned;
    }


    public Calendar getProgramTime() {
        return programTime;
    }


    public void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }


    public boolean isProgramAction() {
        return programAction;
    }


    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }


}
