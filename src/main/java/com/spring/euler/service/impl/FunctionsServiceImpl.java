package com.spring.euler.service.impl;

import com.spring.euler.domain.TimedSolution;
import com.spring.euler.helper.*;
import com.spring.euler.service.FunctionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FunctionsServiceImpl implements FunctionsService {
    public Mono<TimedSolution> alphabeticalValue(String text) { return Mono.just(TimerHelper.run(() -> StringHelper.alphabeticalValue(text), true)); }

    public Mono<TimedSolution> isPrime(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.isPrime(n), true)); }
    public Mono<TimedSolution> findNextPrime(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.findNextPrime(n), true)); }
    public Mono<TimedSolution> primeFactors(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.primeFactors(n), true)); }

    public Mono<TimedSolution> isPalindrome(Integer value) { return Mono.just(TimerHelper.run(() -> StringHelper.isPalindrome(String.valueOf(value)), true)); }

    public Mono<TimedSolution> isPandigital(Long value) { return Mono.just(TimerHelper.run(() -> MathsHelper.isPandigital(value), true)); }

    public Mono<TimedSolution> lowestCommonMultiple(Integer x, Integer y) { return Mono.just(TimerHelper.run(() -> MathsHelper.lowestCommonMultiple(x, y), true)); }
    public Mono<TimedSolution> greatestCommonDivisor(Integer x, Integer y) { return Mono.just(TimerHelper.run(() -> MathsHelper.greatestCommonDivisor(x, y), true)); }

    public Mono<TimedSolution> sieveOfEratosthenes(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.sieveOfEratosthenes(value), true)); }

    public Mono<TimedSolution> triangleNumber(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.triangleNumber(value), true)); }
    public Mono<TimedSolution> isTriangle(Integer t) { return Mono.just(TimerHelper.run(() -> MathsHelper.isTriangle(t), true)); }

    public Mono<TimedSolution> pentagonalNumber(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.pentagonalNumber(value), true)); }
    public Mono<TimedSolution> isPentagonal(Integer p) { return Mono.just(TimerHelper.run(() -> MathsHelper.isPentagonal(p), true)); }

    public Mono<TimedSolution> hexagonalNumber(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.hexagonalNumber(value), true)); }
    public Mono<TimedSolution> isHexagonal(Integer h) { return Mono.just(TimerHelper.run(() -> MathsHelper.isHexagonal(h), true)); }

    public Mono<TimedSolution> findDivisors(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.findDivisors(value), true)); }

    public Mono<TimedSolution> isAbundant(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.isAbundant(value), true)); }

    public Mono<TimedSolution> binomialExpansion(Integer n, Integer k) { return Mono.just(TimerHelper.run(() -> MathsHelper.binomial(n, k), true)); }

    public Mono<TimedSolution> factorial(Integer value) { return Mono.just(TimerHelper.run(() -> BigIntegerHelper.factorial(value), true)); }

    public Mono<TimedSolution> readAllFileLines(String absolutePath) { return Mono.just(TimerHelper.run(() -> FilesHelper.readAllLines(absolutePath), true)); }

    public Mono<TimedSolution> permutations(String permuteObject) { return Mono.just(TimerHelper.run(() -> StringHelper.permutations(permuteObject), true)); }
}