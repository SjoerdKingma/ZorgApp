package Consult;

import Consult.Consult;
import Consult.ConsultType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TandartsConsult extends Consult {
    public static ArrayList<ConsultType> prices;
    public TandartsConsult(LocalDateTime date, ConsultType price){
        super(DepartmentName.Tandarts, date, price);
    }
}
