package com.spring.Euler.service.impl;

import com.spring.Euler.domain.Response;
import com.spring.Euler.domain.mappers.ResponseMapper;
import com.spring.Euler.helper.BigIntegerHelper;
import com.spring.Euler.helper.FilesHelper;
import com.spring.Euler.helper.MathsHelper;
import com.spring.Euler.helper.StringHelper;
import com.spring.Euler.service.FunctionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class FunctionsServiceImpl implements FunctionsService {
    private final ResponseMapper responseMapper;

    public Mono<Response> isPrime(Integer n) {
        return Mono.just(responseMapper.generate(
                "Determine whether the number " + n + " is prime.",
                MathsHelper.isPrime(n),
                null,
                false
        ));
    }

    public Mono<Response> findNextPrime(Integer n) {
        return Mono.just(responseMapper.generate(
                "Determine first prime number greater than " + n + ".",
                MathsHelper.findNextPrime(n),
                null,
                false
        ));
    }

    public Mono<Response> isPalindrome(Integer value) {
        return Mono.just(responseMapper.generate(
                "Determine whether " + value + " is a palindrome.",
                MathsHelper.isPalindrome(value),
                null,
                false
        ));
    }

    public Mono<Response> lowestCommonMultiple(Integer x, Integer y) {
        return Mono.just(responseMapper.generate(
                "Determine the lowest common multiple of " + x + " and " + y + ".",
                MathsHelper.lowestCommonMultiple(x, y),
                null,
                false
        ));
    }

    public Mono<Response> greatestCommonDivisor(Integer x, Integer y) {
        return Mono.just(responseMapper.generate(
                "Determine the greatest common divisor of " + x + " and " + y + ".",
                MathsHelper.greatestCommonDivisor(x, y),
                null,
                false
        ));
    }

    public Mono<Response> sieveOfEratosthenes(Integer value) {
        return Mono.just(responseMapper.generate(
                "Use the Sieve of Eratosthenes method to determine all prime numbers below " + value + ".",
                MathsHelper.sieveOfEratosthenes(value),
                "This method should be fast, but it is not the fastest possible. Please be aware of this, and the possible number of results, when using this method.",
                false
        ));
    }

    public Mono<Response> triangleNumber(Integer value) {
        return Mono.just(responseMapper.generate(
                "Generate the " + value + "th triangle number.",
                MathsHelper.triangleNumber(value),
                null,
                false
        ));
    }

    public Mono<Response> findDivisors(Integer value) {
        return Mono.just(responseMapper.generate(
                "Generate all divisors of " + value + ".",
                MathsHelper.findDivisors(value),
                null,
                false
        ));
    }

    public Mono<Response> isAbundant(Integer value) {
        return Mono.just(responseMapper.generate(
                "Determine whether " + value + " is an abundant number.",
                MathsHelper.isAbundant(value),
                "An abundant number is one whether the sum of its divisors is greater than itself.",
                false
        ));
    }

    public Mono<Response> binomialExpansion(Integer n, Integer k) {
        return Mono.just(responseMapper.generate(
                "Calculate the binomial expansion of " + n + "C" + k + ".",
                MathsHelper.binomial(n, k),
                null,
                false
        ));
    }

    public Mono<Response> factorial(Integer value) {
        return Mono.just(responseMapper.generate(
                "Calculate " + value + "!",
                BigIntegerHelper.factorial(value),
                "This method uses BigInteger to go beyond the 32-bit integer maximum, but be aware of performance concerns for extremely high values.",
                false
        ));
    }

    public Mono<Response> readAllFileLines(String absolutePath) {
        return Mono.just(responseMapper.generate(
                "Attempt to read all lines of the file at this path: " + absolutePath,
                FilesHelper.readAllLine(absolutePath),
                null,
                false
        ));
    }

    public Mono<Response> permutations(String permuteObject) {
        return Mono.just(responseMapper.generate(
                "Generate all permutations of " + permuteObject + ".",
                StringHelper.permutations(permuteObject),
                "This method uses recursion, so please be aware of this when choosing values to submit.",
                false
        ));
    }
}
