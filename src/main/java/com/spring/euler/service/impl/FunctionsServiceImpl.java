package com.spring.euler.service.impl;

import com.spring.euler.domain.TimedSolution;
import com.spring.euler.helper.*;
import com.spring.euler.service.FunctionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

@Slf4j
@Service
@RequiredArgsConstructor
public class FunctionsServiceImpl implements FunctionsService {
    public Mono<TimedSolution> isLychrel(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.isLychrel(n))); }

    public Mono<TimedSolution> alphabeticalValue(String text) { return Mono.just(TimerHelper.run(() -> StringHelper.alphabeticalValue(text))); }

    public Mono<TimedSolution> isPrime(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.isPrime(n))); }
    public Mono<TimedSolution> findNextPrime(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.findNextPrime(n))); }
    public Mono<TimedSolution> primeFactors(Integer n) { return Mono.just(TimerHelper.run(() -> MathsHelper.primeFactors(n))); }

    public Mono<TimedSolution> isPalindrome(Integer value) { return Mono.just(TimerHelper.run(() -> StringHelper.isPalindrome(String.valueOf(value)))); }

    public Mono<TimedSolution> isPandigital(Long value) { return Mono.just(TimerHelper.run(() -> MathsHelper.isPandigital(value))); }

    public Mono<TimedSolution> lowestCommonMultiple(Integer x, Integer y) { return Mono.just(TimerHelper.run(() -> MathsHelper.lowestCommonMultiple(x, y))); }
    public Mono<TimedSolution> greatestCommonDivisor(Integer x, Integer y) { return Mono.just(TimerHelper.run(() -> MathsHelper.greatestCommonDivisor(x, y))); }

    public Mono<TimedSolution> sieveOfEratosthenes(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.sieveOfEratosthenes(value))); }

    public Mono<TimedSolution> triangleNumber(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.triangleNumber(value))); }
    public Mono<TimedSolution> isTriangle(Integer t) { return Mono.just(TimerHelper.run(() -> MathsHelper.isTriangle(t))); }

    public Mono<TimedSolution> pentagonalNumber(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.pentagonalNumber(value))); }
    public Mono<TimedSolution> isPentagonal(Integer p) { return Mono.just(TimerHelper.run(() -> MathsHelper.isPentagonal(p))); }

    public Mono<TimedSolution> hexagonalNumber(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.hexagonalNumber(value))); }
    public Mono<TimedSolution> isHexagonal(Integer h) { return Mono.just(TimerHelper.run(() -> MathsHelper.isHexagonal(h))); }

    public Mono<TimedSolution> findDivisors(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.findDivisors(value))); }

    public Mono<TimedSolution> isAbundant(Integer value) { return Mono.just(TimerHelper.run(() -> MathsHelper.isAbundant(value))); }

    public Mono<TimedSolution> binomialExpansion(Integer n, Integer k) { return Mono.just(TimerHelper.run(() -> BigIntegerHelper.binomial(n, k))); }

    public Mono<TimedSolution> factorial(Integer value) { return Mono.just(TimerHelper.run(() -> BigIntegerHelper.factorial(value))); }

    public Mono<TimedSolution> readAllFileLines(String absolutePath) { return Mono.just(TimerHelper.run(() -> FilesHelper.readAllLines(absolutePath))); }

    public Mono<TimedSolution> permutations(String permuteObject) { return Mono.just(TimerHelper.run(() -> StringHelper.permutations(permuteObject))); }
}