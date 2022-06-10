import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmartPlug extends SmartObject implements Programmable {

    private boolean status;
    private Calendar programTime;
    private boolean programAction; //if the next action will be "on" it's true



    public SmartPlug(String alias, String macId) {
        super.setAlias(alias);
        super.setMacId(macId);
    }





    @Override
    public boolean controlConnection() {
        if (!this.isConnectionStatus()) {
            System.out.println("This device is not connected. SmartPlug -> " + this.getAlias());
            return false;
        } else {

            return true;
        }
    }




    @Override
    public void SmartObjectToString() {

        System.out.println("This is SmartPlug device " + this.getAlias());
        System.out.println("    MacId: " + this.getMacId());
        System.out.println("    IP: " + this.getIP());

    }




    public void turnOn() {

        if (this.controlConnection()) {

            if (this.programAction) {
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                this.setStatus(true);
                System.out.println("Smart Plug - " + this.getAlias() + " is turned on now (Current time: " + strDate + ")");
                this.setProgramAction(false);

            } else {
                System.out.println("Smart Plug - " + this.getAlias() + " has been already turned on");
            }

        }
    }




    public void turnOff() {

        if (this.controlConnection()) {

            if (!this.programAction) {
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                this.setStatus(false);
                System.out.println("Smart Plug - " + this.getAlias() + " is turned off now (Current time: " + strDate + ")");
                this.setProgramAction(true);

            } else {
                System.out.println("Smart Plug - " + this.getAlias() + " has been already turned off");
            }
        }

    }




    @Override
    public boolean testObject() {

        if (this.controlConnection()) {

            this.SmartObjectToString();
            this.turnOn();
            this.turnOff();
            System.out.println("Test completed for SmartPlug");
            return true;
        } else {
            return false;
        }

    }




    @Override
    public boolean shutDownObject() {

        if (this.controlConnection()) {
            if (this.isStatus()) {
                this.SmartObjectToString();
                this.turnOff();
            }
            return true;
        } else {
            return false;
        }

    }





    public void setTimer(int seconds) {

        if (this.controlConnection()) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, seconds);
            this.setProgramTime(calendar);

            if (!this.programAction) {
                System.out.println("Smart plug - " + this.getAlias() + " will be turned off " + seconds + " seconds later!");

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(date);

                System.out.println("(Current time: " + strDate + ")");
            } else {
                System.out.println("Smart plug - " + this.getAlias() + " will be turned on " + seconds + " seconds later!");

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
                if ((this.getProgramTime().compareTo(Calendar.getInstance()) == 0) && !this.programAction) {
                    System.out.println("RunProgram -> Smart Plug - " + this.getAlias());
                    this.turnOff();
                } else if ((this.getProgramTime().compareTo(Calendar.getInstance()) == 0) && !this.programAction) {
                    System.out.println("RunProgram -> Smart Plug - " + this.getAlias());
                    this.turnOn();
                }
            }

        }
    }




    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
