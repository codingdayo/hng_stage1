package codingdayo.hng_stage1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NumberController {

        private final RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/classify-number")
        public ResponseEntity<?> classifyNumber(@RequestParam("number") String numberParam) {
            int number;
            try {
                number = Integer.parseInt(numberParam);
            } catch (NumberFormatException e) {
                Map<String, Object> errorResponse = new LinkedHashMap<>();
                errorResponse.put("number", numberParam);
                errorResponse.put("error", true);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            boolean isPrime = isPrime(number);
            boolean isPerfect = isPerfect(number);

            List<String> properties = new ArrayList<>();
            if (isArmstrong(number)) {
                properties.add("armstrong");
            }

            properties.add((number % 2 == 0) ? "even" : "odd");

            int digitSum = calculateDigitSum(number);
            String funFact;
            try {
                funFact = restTemplate.getForObject("http://numbersapi.com/" + number, String.class);
            } catch (Exception e) {
                funFact = "No fun fact available at the moment.";
            }


            Map<String, Object> response = new LinkedHashMap<>();
            response.put("number", number);
            response.put("is_prime", isPrime);
            response.put("is_perfect", isPerfect);
            response.put("properties", properties);
            response.put("digit_sum", digitSum);
            response.put("fun_fact", funFact);

            return ResponseEntity.ok(response);
        }


        private boolean isPrime(int n) {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }

        private boolean isPerfect(int n) {
            if (n <= 0) return false;
            int sum = 1;
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    sum += i;
                }
            }
            return sum == n;
        }

      private boolean isArmstrong(int n) {
            if (n < 0)
                return false;
            String numStr = String.valueOf(n);
            int numDigits = numStr.length();
            int sum = 0;
            int temp = n;
            while (temp > 0) {
                int digit = temp % 10;
                sum += Math.pow(digit, numDigits);
                temp /= 10;
            }
            return sum == n;
        }


        private int calculateDigitSum(int n) {
            int sum = 0;
            int temp = Math.abs(n);
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            return sum;
        }
    }
