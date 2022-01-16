package com.app.zlobek.util.shift;

import com.app.zlobek.entity.Shift;
import lombok.Data;

@Data
public class ShiftWithBabysitter {

    private int idOfBabysitter;
    private Shift shift;

    public ShiftWithBabysitter() {
    }

    public ShiftWithBabysitter(int idOfBabysitter) {
        this.idOfBabysitter = idOfBabysitter;
        shift = new Shift();
    }

    public ShiftWithBabysitter(Shift shift, int idOfBabysitter) {
        this.idOfBabysitter = idOfBabysitter;
        this.shift = shift;
    }
}
