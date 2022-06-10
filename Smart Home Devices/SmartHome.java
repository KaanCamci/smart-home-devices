import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {

    private ArrayList<SmartObject> smartObjectList;

    public SmartHome() {

        smartObjectList = new ArrayList<SmartObject>();
    }




    public boolean addSmartObject(SmartObject smartObject) {

        System.out.println("--------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------\n" +
                "Adding new SmartObject\n" +
                "--------------------------------------------------------------------------\n");

        smartObject.connect("10.0.0." + (100 + smartObjectList.size()));

        smartObject.testObject();

        return smartObjectList.add(smartObject);
    }





    public boolean removeSmartObject(SmartObject smartObject) {

        return smartObjectList.remove(smartObject);
    }





    public void controlLocation(boolean onCome) {

        for (SmartObject smartObject : smartObjectList) {
            if (smartObject instanceof SmartLight) {

                if (onCome) {
                    System.out.println("--------------------------------------------------------------------------\n" +
                            "--------------------------------------------------------------------------\n" +
                            "LocationControl : OnCome\n" +
                            "--------------------------------------------------------------------------");
                    ((SmartLight) smartObject).onCome();
                } else {
                    System.out.println("--------------------------------------------------------------------------\n" +
                            "--------------------------------------------------------------------------\n" +
                            "LocationControl : OnLeave\n" +
                            "--------------------------------------------------------------------------");
                    ((SmartLight) smartObject).onLeave();
                }
            }

        }

    }






    public void controlMotion(boolean hasMotion, boolean isDay) {

        System.out.println("--------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------\n" +
                "MotionControl: HasMotion, isDay\n" +
                "--------------------------------------------------------------------------\n");

        for (SmartObject smartObject : smartObjectList) {
            if (smartObject instanceof SmartCamera) {


                ((SmartCamera) smartObject).controlMotion(hasMotion, isDay);

            }
        }
    }






    public void controlProgrammable() {

        System.out.println("--------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------\n" +
                "Programmable: runProgram\n" +
                "--------------------------------------------------------------------------");

        for (SmartObject smartObject : smartObjectList) {
            if (smartObject instanceof Programmable) {
                ((Programmable) smartObject).runProgram();

            }
        }
    }






    public void controlTimer(int seconds) {

        System.out.println("--------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------\n" +
                "Programmable: Timer = " + seconds + " seconds\n" +
                "--------------------------------------------------------------------------");
        for (SmartObject smartObject : smartObjectList) {

            if (smartObject instanceof Programmable) {

                if (seconds > 0) {
                    ((Programmable) smartObject).setTimer(seconds);
                } else if (seconds == 0) {
                    ((Programmable) smartObject).cancelTimer();
                }
            }
        }
    }





    public void controlTimerRandomly() {

        System.out.println("--------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------\n" +
                "Programmable: Timer = 0, 5 or 10 seconds randomly\n" +
                "--------------------------------------------------------------------------");


        for (SmartObject smartObject : smartObjectList) {

            if (smartObject instanceof Programmable) {

                int random_int = (int) Math.floor(Math.random() * (2 + 1) + 0);

                int seconds1 = random_int * 5;

                if (seconds1 == 0) {
                    ((Programmable) smartObject).cancelTimer();
                } else {
                    ((Programmable) smartObject).setTimer(seconds1);
                }

            }
        }
    }








    public void sortCameras() {

        System.out.println("--------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------\n" +
                "Sort Smart Cameras\n" +
                "--------------------------------------------------------------------------");

        int numberOfCameras = 0;

        for (SmartObject smartObject : smartObjectList) {
            if (smartObject instanceof Comparable) {
                numberOfCameras++;
            }
        }

        int[] smartCamerasbatteryLifesArray = new int[numberOfCameras];

        int a = 0;
        for (SmartObject smartObject : smartObjectList) {
            if (smartObject instanceof Comparable) {
                SmartCamera xx = (SmartCamera) smartObject;
                smartCamerasbatteryLifesArray[a] = xx.getBatteryLife();
                a++;
            }
        }

        Arrays.sort(smartCamerasbatteryLifesArray);

        int b = 0;

        for (int u = 0; u < numberOfCameras; u++) {

            for (int o = 0; o < smartObjectList.size(); o++) {
                if (smartObjectList.get(o) instanceof SmartCamera) {
                    SmartCamera yy = (SmartCamera) smartObjectList.get(o);

                    if (yy.getBatteryLife() == smartCamerasbatteryLifesArray[u]) {
                        System.out.println(yy.toString());
                    }
                }
            }

        }

    }







    public ArrayList<SmartObject> getSmartObjectList() {
        return smartObjectList;
    }

    public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
        this.smartObjectList = smartObjectList;
    }
}
