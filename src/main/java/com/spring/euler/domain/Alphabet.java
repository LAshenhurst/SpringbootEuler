package com.spring.euler.domain;

public enum Alphabet {
    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

    public static int getIndex(String val) { return valueOf(val).ordinal() + 1; }
}