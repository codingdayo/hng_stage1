
---

## Stage 1 Backend- Number Classification API

   **Technologies Used**
    - Java 8+
    - Spring Boot


1. **Input Validation:**  
   The controller attempts to parse the `number` query parameter. If parsing fails, it returns a 400 error JSON.

2. **Mathematical Checks:**  
   - **Prime:** Uses a basic loop (up to the square root) to determine primality.
   - **Perfect:** Sums the proper divisors and compares with the number.
   - **Armstrong:** Raises each digit to the power equal to the number of digits and sums them.
   - **Digit Sum:** Sums all digits (using the absolute value).

3. **Properties List:**  
   If the number is Armstrong, `"armstrong"` is added to the properties list followed by `"odd"` or `"even"` (based on the number’s parity). Otherwise, only the parity is added.

4. **Fun Fact:**  
  Fetches a fun fact about the number from the api:- http://numbersapi.com/#42

- **CORS Enabled:** The API allows cross-origin requests from any domain.

5. **CORS:**  
   The `@CrossOrigin` annotation on the controller ensures that the API can be called from web pages hosted on different domains.



---

