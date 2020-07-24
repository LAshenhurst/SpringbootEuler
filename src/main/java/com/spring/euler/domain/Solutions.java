package com.spring.euler.domain;

import com.spring.euler.service.impl.solutions.*;

public enum Solutions {
    ONE          { @Override public Object run() { return Problem1.run(); } },
    TWO          { @Override public Object run() { return Problem2.run(); } },
    THREE        { @Override public Object run() { return Problem3.run(); } },
    FOUR         {  @Override public Object run() { return Problem4.run(); } },
    FIVE         { @Override public Object run() { return Problem5.run(); } },
    SIX          {  @Override public Object run() { return Problem6.run(); } },
    SEVEN        { @Override public Object run() { return Problem7.run(); } },
    EIGHT        { @Override public Object run() { return Problem8.run(); } },
    NINE         { @Override public Object run() { return Problem9.run(); } },
    TEN          { @Override public Object run() { return Problem10.run(); } },
    ELEVEN       { @Override public Object run() { return Problem11.run(); } },
    TWELVE       { @Override public Object run() { return Problem12.run(); } },
    THIRTEEN     { @Override public Object run() { return Problem13.run(); } },
    FOURTEEN     { @Override public Object run() { return Problem14.run(); } },
    FIFTEEN      { @Override public Object run() { return Problem15.run(); } },
    SIXTEEN      { @Override public Object run() { return Problem16.run(); } },
    SEVENTEEN    { @Override public Object run() { return Problem17.run(); } },
    EIGHTEEN     { @Override public Object run() { return Problem18.run(); } },
    NINETEEN     { @Override public Object run() { return Problem19.run(); } },
    TWENTY       { @Override public Object run() { return Problem20.run(); } },
    TWENTY_ONE   { @Override public Object run() { return Problem21.run(); } },
    TWENTY_TWO   { @Override public Object run() { return Problem22.run(); } },
    TWENTY_THREE { @Override public Object run() { return Problem23.run(); } },
    TWENTY_FOUR  { @Override public Object run() { return Problem24.run(); } },
    TWENTY_FIVE  { @Override public Object run() { return Problem25.run(); } },
    TWENTY_SIX   { @Override public Object run() { return Problem26.run(); } },
    TWENTY_SEVEN { @Override public Object run() { return Problem27.run(); } },
    TWENTY_EIGHT { @Override public Object run() { return Problem28.run(); } },
    TWENTY_NINE  { @Override public Object run() { return Problem29.run(); } };

    public abstract Object run();
}
