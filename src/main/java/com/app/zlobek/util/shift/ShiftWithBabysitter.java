package com.app.zlobek.util.shift;

import com.app.zlobek.entity.Shift;
import lombok.Data;

@Data
public class ShiftWithBabysitter {

    private int idOfBabysitter;
    private Shift shift;

    public ShiftWithBabysitter(int idOfBabysitter) {
        this.idOfBabysitter = idOfBabysitter;
        shift = new Shift();
    }
}
