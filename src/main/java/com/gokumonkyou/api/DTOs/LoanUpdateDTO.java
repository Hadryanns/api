package com.gokumonkyou.api.DTOs;

import java.time.LocalDate;

public class LoanUpdateDTO {
    private boolean returned;
    private LocalDate returnDate;

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

