package model;

/**
 * Created by R2 on 28.07.2015.
 */
public enum TypeOfField {
    EMPTY, OCCUPIED, MISS, HIT, INJURED, DOUBLESHOOT;

    static TypeOfField getType(int num){
        return TypeOfField.values()[num-1];
    }
}
