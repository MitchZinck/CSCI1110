package com.mzinck.a4;

/**
 * @author Mitchell Zinck <github.com/mitchzinck>
 *         Program that represents an Event
 */
public class Event {

    private int dW, sH, sM, eH, eM;
    private String title;
    private boolean errorParams = false;

    /**
     * Enumerator that represents days and their respective values
     */
    enum Days {

        Sun(0),
        Mon(1),
        Tue(2),
        Wed(3),
        Thu(4),
        Fri(5),
        Sat(6);

        int value;

        Days(int value) {
            this.value = value;
        }

    }

    /**
     * Creates an event.
     *
     * @param dW    The day of the week
     * @param sH    The starting hour
     * @param sM    The starting minute
     * @param eH    The ending hour
     * @param eM    The ending minute
     * @param title The title
     */
    public Event(int dW, int sH, int sM, int eH, int eM, String title) {
        this.title = title;
        this.dW = dW >= 0 && dW <= 6 ? dW : error(0);
        this.sH = sH >= 0 && (sH <= 23 || sH == 24 && sM == 0) ? sH : error(0);
        this.sM = sM >= 0 && sM <= 59 ? sM : error(0);

        if (eH >= 0 && (eH <= 23 || eH == 24 && eM == 0)) {
            this.eH = eH > this.sH ? eH : error(sH);
        } else {
            this.eH = error(0);
        }

        if (eM >= 0 && eM <= 59) {
            if ((this.eH != eH && this.eH == this.sH) || (this.eH == this.sH && eM < this.sM)) {
                this.eM = this.sM;
            } else {
                this.eM = this.eH > 0 ? eM : error(sM);
            }
        } else {
            this.eM = error(0);
        }
    }

    /**
     * Easy way to print out an error while also giving the error variable
     * an appropriate value
     */
    public int error(int returnValue) {
        if (errorParams == false) {
            System.out.println("Invalid Event parameters");
            errorParams = true;
        }
        return returnValue;
    }

    /**
     * Creates an event. No ending time is given so we set it to
     * an hour past the starting time
     *
     * @param dW    The day of the week
     * @param sH    The starting hour
     * @param sM    The starting minute
     * @param title The title
     */
    public Event(int dW, int sH, int sM, String title) {
        this.title = title;
        this.dW = dW >= 0 && dW <= 6 ? dW : error(0);
        this.sH = sH >= 0 && (sH <= 23 || sH == 24 && sM == 0) ? sH : error(0);
        this.sM = sM >= 0 && sM <= 59 ? sM : error(0);

        if (24 - this.sH > 0) {
            this.eH = this.sH + 1;
            this.eM = this.sM;
        } else {
            this.eH = 24;
            this.eM = 0;
        }
    }

    /**
     * Creates an event. No ending time is given so we set it to
     * an hour past the starting time. Date of the week is also as a string
     *
     * @param dW    The day of the week
     * @param sH    The starting hour
     * @param sM    The starting minute
     * @param title The title
     */
    public Event(String dW, int sH, int sM, String title) {
        this.title = title;
        this.sH = sH >= 0 && (sH <= 23 || sH == 24 && sM == 0) ? sH : error(0);
        this.sM = sM >= 0 && sM <= 59 ? sM : error(0);

        if (24 - this.sH > 0) {
            this.eH = this.sH + 1;
            this.eM = this.sM;
        } else {
            this.eH = 24;
            this.eM = 0;
        }

        boolean found = false;
        for (Days d : Days.values()) {
            if (d.name().equals(dW)) {
                found = true;
                this.dW = d.value;
                break;
            }
        }

        if (found == false) {
            this.dW = error(0);
        }
    }

    /**
     * Creates an event. No ending time is given so we set it to
     * an hour past the starting time. Starting minute is also set to 0
     *
     * @param dW    The day of the week
     * @param sH    The starting hour
     * @param title The title
     */
    public Event(int dW, int sH, String title) {
        this.title = title;
        this.dW = dW >= 0 && dW <= 6 ? dW : error(0);
        this.sH = sH >= 0 && (sH <= 23 || sH == 24 && sM == 0) ? sH : error(0);
        this.sM = 0;

        if (24 - this.sH > 0) {
            this.eH = this.sH + 1;
            this.eM = sM;
        } else {
            this.eH = 24;
            this.eM = 0;
        }
    }

    /**
     * Creates an event. No ending time is given so we set it to
     * an hour past the starting time. Starting minute is also set to 0
     *
     * @param dW    The day of the week
     * @param sH    The starting hour
     * @param title The title
     */
    public Event(String dW, int sH, String title) {
        this.title = title;
        this.sH = sH >= 0 && (sH <= 23 || sH == 24 && sM == 0) ? sH : error(0);
        this.sM = 0;

        if (24 - this.sH > 0) {
            this.eH = this.sH + 1;
            this.eM = this.sM;
        } else {
            this.eH = 24;
            this.eM = 0;
        }

        boolean found = false;
        for (Days d : Days.values()) {
            if (d.name().equals(dW)) {
                found = true;
                this.dW = d.value;
                break;
            }
        }

        if (found == false) {
            this.dW = error(0);
        }
    }

    /**
     * Creates an event that is parsed as a String.
     *
     * @param eventDescription String of the event to create
     */
    public Event(String eventDescription) {
        String[] input = eventDescription.split(" ");
        int dW = 0;
        boolean found = false;
        for (Days d : Days.values()) {
            if (d.name().equals(input[0])) {
                found = true;
                dW = d.value;
                break;
            }
        }

        if (found == false) {
            dW = 8;
        }

        String[] input2 = input[1].split(":");
        int sH = Integer.parseInt(input2[0]);
        int sM = Integer.parseInt(input2[1].split("-")[0]);
        int eH = Integer.parseInt(input2[1].split("-")[1]);
        int eM = Integer.parseInt(input2[2]);

        String titl = "";
        for (int i = 2; i < input.length; i++) {
            titl += input[i] + " ";
        }

        Event e = new Event(dW, sH, sM, eH, eM, titl);

        this.dW = e.getWeekDay();
        this.sH = e.getStartingHour();
        this.sM = e.getStartingMinute();
        this.eH = e.getEndingHour();
        this.eM = e.getEndingMinute();
        this.title = e.getTitle();
    }

    /**
     * Sets the starting time of the event
     *
     * @param sH The starting hour
     * @param sM The starting minute
     */
    public void setStart(int sH, int sM) {
        this.sH = sH >= 0 && (sH <= 23 || sH == 24 && sM == 0) ? sH : error(this.sH);
        this.sM = sM >= 0 && sM <= 59 ? sM : error(this.sM);
    }

    /**
     * Sets the ending time of an event
     *
     * @param eH The ending hour
     * @param eM The ending minute
     */
    public void setEnd(int eH, int eM) {
        if (eH >= 0 && (eH <= 23 || eH == 24 && eM == 0)) {
            this.eH = eH > sH ? eH : sH;
        } else {
            this.eH = error(this.eH);
        }

        if (eM >= 0 && eM <= 59) {
            this.eM = eH > sH && eM > sM ? eM : sM;
        } else {
            this.eM = error(this.eM);
        }
    }

    /**
     * @return The day of the week
     */
    public int getWeekDay() {
        return dW;
    }

    /**
     * @return The starting hour of the event
     */
    public int getStartingHour() {
        return sH;
    }

    /**
     * @return The starting minute of the event
     */
    public int getStartingMinute() {
        return sM;
    }

    /**
     * @return The ending hour of the event
     */
    public int getEndingHour() {
        return eH;
    }

    /**
     * @return The ending minute of the event
     */
    public int getEndingMinute() {
        return eM;
    }

    /**
     * @return The title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The duration of the event in minutes
     */
    public int getDuration() {
        return 60 * (eH - sH) + (eM - sM);
    }

    /**
     * Checks if two events overlap
     * @param e2
     * @return
     */
    public boolean overlaps(Event e2) {
        if (e2.eH == this.sH && e2.eM >= this.sM + 1) {
            return true;
        } else if (e2.eH > this.sH && e2.eH < this.eH) {
            return true;
        } else if (this.eH == e2.sH && this.eM >= e2.sM + 1) {
            return true;
        } else if (this.eH > e2.sH && this.eH < e2.eH) {
            return true;
        }

        return false;
    }

    /**
     * Parses the event into a String
     * @return  The event as a String
     */
    public String toString() {
        String day = "";
        for (Days d : Days.values()) {
            if (d.value == dW) {
                day = d.name();
                break;
            }
        }

        String output = "";
        output += sH < 10 ? "0" + sH : sH;
        output += ":";
        output += sM < 10 ? "0" + sM : sM;
        output += "-";
        output += eH < 10 ? "0" + eH : eH;
        output += ":";
        output += eM < 10 ? "0" + eM : eM;

        return day + " " + output + " " + title;
    }
}
