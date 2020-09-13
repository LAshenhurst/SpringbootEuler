package com.spring.euler.service;

import com.spring.euler.domain.TimedSolution;
import reactor.core.publisher.Mono;

public interface FunctionsService {
    Mono<TimedSolution> alphabeticalValue(String text);

    Mono<TimedSolution> isPrime(Integer n);
    Mono<TimedSolution> findNextPrime(Integer n);
    Mono<TimedSolution> primeFactors(Integer n);

    Mono<TimedSolution> isPalindrome(Integer value);

    Mono<TimedSolution> isPandigital(Long value);

    Mono<TimedSolution> lowestCommonMultiple(Integer x, Integer y);
    Mono<TimedSolution> greatestCommonDivisor(Integer x, Integer y);

    Mono<TimedSolution> sieveOfEratosthenes(Integer x);

    Mono<TimedSolution> triangleNumber(Integer value);
    Mono<TimedSolution> isTriangle(Integer t);

    Mono<TimedSolution> pentagonalNumber(Integer value);
    Mono<TimedSolution> isPentagonal(Integer p);

    Mono<TimedSolution> hexagonalNumber(Integer value);
    Mono<TimedSolution> isHexagonal(Integer h);

    Mono<TimedSolution> findDivisors(Integer value);

    Mono<TimedSolution> isAbundant(Integer value);

    Mono<TimedSolution> binomialExpansion(Integer n, Integer k);

    Mono<TimedSolution> factorial(Integer value);

    Mono<TimedSolution> readAllFileLines(String absolutePath);

    Mono<TimedSolution> permutations(String permuteObject);
}
