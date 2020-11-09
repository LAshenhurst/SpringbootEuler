package com.spring.euler.controller;

import com.spring.euler.domain.Response;
import com.spring.euler.domain.TimedSolution;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.service.FunctionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Functions"})
@RequestMapping("/v1/functions")
public class FunctionsController {
    private final FunctionsService functionsService;

    @GetMapping("/lychrel")
    @ApiOperation(value = "Determine whether a provided integer is a Lychrel number.")
    public Mono<Response> lychrel(@Valid @RequestParam Integer value) {
        String task = "Determine whether a provided integer is a Lychrel number.";
        String notes = "A Lychrel number is one where repeatedly adding the number and its reverse never forms a palindrome.";
        return functionsService.isLychrel(value).map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    @GetMapping("/alphabetical-value")
    @ApiOperation(value = "Find the sum of the alphabetical value of the characters in a given string")
    public Mono<Response> alphabeticalValue(@Valid @RequestParam String text) {
        String task = "Determine the sum of the alphabetical value of the characters in a given string.";
        return functionsService.alphabeticalValue(text).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/prime")
    @ApiOperation(value = "Determine whether a provided integer is a prime number or not.")
    public Mono<Response> isPrime(@Valid @RequestParam Integer value) {
        String task = "Determine whether the number " + value + " is prime.";
        return functionsService.isPrime(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/next-prime")
    @ApiOperation(value = "Find the first prime number larger than a provided integer.")
    public Mono<Response> findNextPrime(@Valid @RequestParam Integer value) {
        String task = "Find the first prime number greater than " + value + ".";
        return functionsService.findNextPrime(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/prime-factors")
    @ApiOperation(value = "Find the prime factors of a provided integer")
    public Mono<Response> primeFactors(@Valid @RequestParam Integer value) {
        String task = "Find the prime factors of " + value + ".";
        return functionsService.primeFactors(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/palindrome")
    @ApiOperation(value = "Determine whether a provided integer is a palindrome")
    public Mono<Response> isPalindrome(@Valid @RequestParam Integer value) {
        String task = "Determine whether " + value + " is a palindrome.";
        return functionsService.isPalindrome(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/pandigital")
    @ApiOperation(value = "Determine whether a given string is a pandigital number")
    public Mono<Response> pandigital(@Valid @RequestParam Long value) {
        String task = "Determine whether " + value + " is pandigital.";
        return functionsService.isPandigital(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/lowest-common-multiple")
    @ApiOperation(value = "Find the lowest common multiple of two provided integers x and y")
    public Mono<Response> lowestCommonMultiple(@Valid @RequestParam Integer x, @Valid @RequestParam Integer y) {
        String task = "Find the lowest common multiple of " + x + " and " + y + ".";
        return functionsService.lowestCommonMultiple(x, y).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/greatest-common-divisor")
    @ApiOperation(value = "Find the greatest common divisor of two provided integers x and y")
    public Mono<Response> greatestCommonDivisor(@Valid @RequestParam Integer x, @Valid @RequestParam Integer y) {
        String task = "Find the greatest common divisor of " + x + " and " + y + ".";
        return functionsService.greatestCommonDivisor(x, y).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/sieve-of-eratosthenes")
    @ApiOperation(value = "Find all prime numbers below a given integer using the Sieve of Eratosthenes")
    public Mono<Response> sieveOfEratosthenes(@Valid @RequestParam Integer value) {
        String task = "Find all prime numbers lower than " + value + " using the Sieve of Eratosthenes.";
        String notes = "This method is fast, but not the fastest possible. For extremely large numbers there can be a noticeable compute time.";
        return functionsService.sieveOfEratosthenes(value).map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    @GetMapping("/triangle-number")
    @ApiOperation(value = "Find the nth triangle number")
    public Mono<Response> triangleNumber(@Valid @RequestParam Integer value) {
        String task = "Find the " + value + "th triangle number.";
        return functionsService.triangleNumber(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/triangle")
    @ApiOperation(value = "Determine whether a given number is a triangle number")
    public Mono<Response> isTriangle(@Valid @RequestParam Integer value) {
        String task = "Determine whether " + value + " is a triangle number.";
        return functionsService.isTriangle(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/pentagonal-number")
    @ApiOperation(value = "Find the nth pentagonal number")
    public Mono<Response> pentagonalNumber(@Valid @RequestParam Integer value) {
        String task = "Find the " + value + "th pentagonal number.";
        return functionsService.pentagonalNumber(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/pentagonal")
    @ApiOperation(value = "Determine whether a given number is a pentagonal number")
    public Mono<Response> isPentagonal(@Valid @RequestParam Integer value) {
        String task = "Determine whether " + value + " is a pentagonal number.";
        return functionsService.isPentagonal(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/hexagonal-number")
    @ApiOperation(value = "Find the nth hexagonal number")
    public Mono<Response> hexagonalNumber(@Valid @RequestParam Integer value) {
        String task = "Find the " + value + "th hexagonal number.";
        return functionsService.hexagonalNumber(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/hexagonal")
    @ApiOperation(value = "Determine whether a given number is a hexagonal number")
    public Mono<Response> isHexagonal(@Valid @RequestParam Integer value) {
        String task = "Determine whether " + value + " is a hexagonal number.";
        return functionsService.isHexagonal(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/divisors")
    @ApiOperation(value = "Find all divisors of the provided integer")
    public Mono<Response> findDivisors(@Valid @RequestParam Integer value) {
        String task = "Find all divisors of " + value + ".";
        return functionsService.findDivisors(value).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/abundant")
    @ApiOperation(value = "Determine whether a provided integer is an abundant number")
    public Mono<Response> isAbundant(@Valid @RequestParam Integer value) {
        String task = "Determine whether " + value + " is an abundant number.";
        String notes = "An abundant number is one whether the sum of its divisors is greater than itself.";
        return functionsService.isAbundant(value).map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    @GetMapping("/binomial-expansion")
    @ApiOperation(value = "Find the binomial expansion of two given integers n and k")
    public Mono<Response> binomialExpansion(@Valid @RequestParam Integer n, @Valid @RequestParam Integer k) {
        String task = "Find the binomial expansion of " + n + "C" + k + ".";
        return functionsService.binomialExpansion(n, k).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/factorial")
    @ApiOperation(value = "Find the factorial of a given integer.")
    public Mono<Response> factorial(@Valid @RequestParam Integer value) {
        String task = "Find " + value + "!";
        String notes = "This uses BigInteger to go beyond the 32-bit integer maximum. For extremely large numbers there can be a noticeable compute time.";
        return functionsService.factorial(value).map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    @GetMapping("/read-all-lines")
    @ApiOperation(value = "Read all lines of a file both local and accessible by this server by providing the absolute path")
    public Mono<Response> readAllLines(@Valid @RequestParam String absolutePath) {
        String task = "Attempt to read all lines of the file at this path: " + absolutePath;
        return functionsService.readAllFileLines(absolutePath).map(timedSolution -> createResponse(task, timedSolution, null));
    }

    @GetMapping("/permutations")
    @ApiOperation(value = "Find all permutations of the provided string")
    public Mono<Response> permutations(@Valid @RequestParam String permuteObject) {
        String task = "Find all permutations of " + permuteObject + ".";
        String notes = "This method uses recursion, so please be aware of this when choosing values to submit.";
        return functionsService.permutations(permuteObject).map(timedSolution -> createResponse(task, timedSolution, notes));
    }

    private Response createResponse(String task, TimedSolution timedSolution, String notes) {
        return ResponseMapper.toResponse(task, timedSolution.getAnswer(), notes, false, timedSolution.getComputeTime());
    }
}