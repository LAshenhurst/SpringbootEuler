package com.spring.euler.service.impl;

import com.spring.euler.domain.Response;
import com.spring.euler.domain.TimedSolution;
import com.spring.euler.domain.mappers.ResponseMapper;
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
    private Response createResponse(String task, TimedSolution timedSolution, String notes) {
        return ResponseMapper.generate(task, timedSolution.getAnswer(), notes, false, timedSolution.getComputeTime());
    }

    public Mono<Response> alphabeticalValue(String text) {
        String task = "Determine the sum of the alphabetical value of the characters in a given string";
        return Mono.just(TimerHelper.run(() -> StringHelper.alphabeticalValue(text), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> isPrime(Integer n) {
        String task = "Determine whether the number " + n + " is prime.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.isPrime(n), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> isPalindrome(Integer value) {
        String task = "Determine whether " + value + " is a palindrome.";
        return Mono.just(TimerHelper.run(() -> StringHelper.isPalindrome(String.valueOf(value)), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> isPandigital(Long value) {
        String task = "Determine whether " + value + " is pandigital.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.isPandigital(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> findNextPrime(Integer n) {
        String task = "Determine first prime number greater than " + n + ".";
        return Mono.just(TimerHelper.run(() -> MathsHelper.findNextPrime(n), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> lowestCommonMultiple(Integer x, Integer y) {
        String task = "Determine the lowest common multiple of " + x + " and " + y + ".";
        return Mono.just(TimerHelper.run(() -> MathsHelper.lowestCommonMultiple(x, y), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> greatestCommonDivisor(Integer x, Integer y) {
        String task = "Determine the greatest common divisor of " + x + " and " + y + ".";
        return Mono.just(TimerHelper.run(() -> MathsHelper.greatestCommonDivisor(x, y), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> sieveOfEratosthenes(Integer value) {
        String task = "Use the Sieve of Eratosthenes method to determine all prime numbers below " + value + ".";
        String notes = "This method should be fast, but it is not the fastest possible. Please be aware of this, and the possible number of results, when using this method.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.sieveOfEratosthenes(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    public Mono<Response> triangleNumber(Integer value) {
        String task = "Generate the " + value + "th triangle number.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.triangleNumber(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> isTriangle(Integer t) {
        String task = "Determine whether " + t + " is a triangle number.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.isTriangle(t), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> pentagonalNumber(Integer value) {
        String task = "Generate the " + value + "th pentagonal number.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.pentagonalNumber(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> isPentagonal(Integer p) {
        String task = "Determine whether " + p + " is a pentagonal number.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.isPentagonal(p), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> findDivisors(Integer value) {
        String task = "Generate all divisors of " + value + ".";
        return Mono.just(TimerHelper.run(() -> MathsHelper.findDivisors(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> isAbundant(Integer value) {
        String task = "Determine whether " + value + " is an abundant number.";
        String notes = "An abundant number is one whether the sum of its divisors is greater than itself.";
        return Mono.just(TimerHelper.run(() -> MathsHelper.isAbundant(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    public Mono<Response> binomialExpansion(Integer n, Integer k) {
        String task = "Calculate the binomial expansion of " + n + "C" + k + ".";
        return Mono.just(TimerHelper.run(() -> MathsHelper.binomial(n, k), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> factorial(Integer value) {
        String task = "Calculate " + value + "!";
        String notes = "This method uses BigInteger to go beyond the 32-bit integer maximum, but be aware of performance concerns for extremely high values.";
        return Mono.just(TimerHelper.run(() -> BigIntegerHelper.factorial(value), true))
                .map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    public Mono<Response> readAllFileLines(String absolutePath) {
        String task = "Attempt to read all lines of the file at this path: " + absolutePath;
        return Mono.just(TimerHelper.run(() -> FilesHelper.readAllLines(absolutePath), true))
                .map(timedSolution -> createResponse(task, timedSolution, null));
    }

    public Mono<Response> permutations(String permuteObject) {
        String task = "Generate all permutations of " + permuteObject + ".";
        String notes = "This method uses recursion, so please be aware of this when choosing values to submit.";
        return Mono.just(TimerHelper.run(() -> StringHelper.permutations(permuteObject), true))
                .map(timedSolution -> createResponse(task, timedSolution, notes));
    }
}