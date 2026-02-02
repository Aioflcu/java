// Global state for employee registry
let employeeRegistry = [];

// Tab Navigation
document.querySelectorAll('.nav-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        const tabId = btn.dataset.tab;
        
        // Remove active class from all tabs
        document.querySelectorAll('.tab-content').forEach(tab => tab.classList.remove('active'));
        document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
        
        // Add active class to current tab
        document.getElementById(tabId).classList.add('active');
        btn.classList.add('active');
    });
});

// ============== CALCULATOR ==============

function performCalculation() {
    const num1 = parseFloat(document.getElementById('calc-num1').value);
    const num2 = parseFloat(document.getElementById('calc-num2').value);
    const operator = document.getElementById('calc-operator').value;

    if (isNaN(num1) || isNaN(num2)) {
        showResult('calculator-result', 'Please enter valid numbers', 'error');
        return;
    }

    let result;
    let operationName = '';

    switch (operator) {
        case '+':
            result = num1 + num2;
            operationName = 'Addition';
            break;
        case '-':
            result = num1 - num2;
            operationName = 'Subtraction';
            break;
        case '*':
            result = num1 * num2;
            operationName = 'Multiplication';
            break;
        case '/':
            if (num2 === 0) {
                showResult('calculator-result', 'Error: Division by zero!', 'error');
                return;
            }
            result = num1 / num2;
            operationName = 'Division';
            break;
        case '%':
            if (num2 === 0) {
                showResult('calculator-result', 'Error: Modulo by zero!', 'error');
                return;
            }
            result = num1 % num2;
            operationName = 'Modulo';
            break;
    }

    let html = `<h4>✓ Calculation Complete</h4>
        <p><strong>Operation:</strong> ${operationName}</p>
        <p><strong>Expression:</strong> ${num1} ${operator} ${num2}</p>
        <p><strong>Result:</strong> <span style="color: #667eea; font-size: 1.4em; font-weight: bold;">${result.toFixed(4)}</span></p>`;
    
    showResult('calculator-result', html, 'success');
}

// ============== COMBINATORIAL (Q3) ==============

let factorialCache = {};

function calculateFactorial(n) {
    if (factorialCache[n]) return factorialCache[n];
    if (n === 0 || n === 1) return 1;
    
    let result = 1;
    for (let i = 2; i <= n; i++) {
        result *= i;
    }
    
    factorialCache[n] = result;
    return result;
}

function calculateNCr() {
    const n = parseInt(document.getElementById('ncr-n').value);
    const r = parseInt(document.getElementById('ncr-r').value);

    if (isNaN(n) || isNaN(r) || n < 0 || r < 0 || r > n) {
        showResult('ncr-result', 'Please enter valid values: 0 ≤ r ≤ n', 'error');
        return;
    }

    const nFactorial = calculateFactorial(n);
    const rFactorial = calculateFactorial(r);
    const nMinusRFactorial = calculateFactorial(n - r);

    const nCr = nFactorial / (nMinusRFactorial * rFactorial);

    let html = `<h4>✓ Combination Calculated</h4>
        <p><strong>Formula:</strong> nCr = n! / ((n-r)! × r!)</p>
        <p><strong>Values:</strong> n=${n}, r=${r}</p>
        <p><strong>Calculation:</strong></p>
        <pre>${n}C${r} = ${n}! / (${n-r}! × ${r}!)
      = ${nFactorial} / (${nMinusRFactorial} × ${rFactorial})
      = ${nFactorial} / ${nMinusRFactorial * rFactorial}</pre>
        <p><strong>Result:</strong> <span style="color: #667eea; font-size: 1.4em; font-weight: bold;">${nCr.toFixed(0)}</span></p>`;
    
    showResult('ncr-result', html, 'success');
}

// ============== MATH TABLE (Q4) ==============

function generateMathTable() {
    const start = parseInt(document.getElementById('table-start').value);
    const end = parseInt(document.getElementById('table-end').value);

    if (isNaN(start) || isNaN(end) || start < 1 || end < start || (end - start) > 100) {
        showResult('table-result', 'Please enter valid range (max 100 numbers)', 'error');
        return;
    }

    let tableData = [];
    let html = `<h4>Mathematical Table</h4>
        <p><strong>Range:</strong> ${start} to ${end}</p>
        <table>
            <tr>
                <th>N</th>
                <th>N²</th>
                <th>√N</th>
                <th>N³</th>
                <th>∛N</th>
            </tr>`;

    for (let n = start; n <= end; n++) {
        const nSquared = n * n;
        const nSquareRoot = Math.sqrt(n);
        const nCube = n * n * n;
        const nCubeRoot = Math.cbrt(n);

        html += `<tr>
            <td>${n}</td>
            <td>${nSquared}</td>
            <td>${nSquareRoot.toFixed(4)}</td>
            <td>${nCube}</td>
            <td>${nCubeRoot.toFixed(4)}</td>
        </tr>`;

        tableData.push({ n, nSquared, nSquareRoot, nCube, nCubeRoot });
    }

    html += `</table>`;

    // Add statistics
    const nValues = tableData.map(d => d.n);
    const n2Values = tableData.map(d => d.nSquared);
    const sqrtValues = tableData.map(d => d.nSquareRoot);
    const n3Values = tableData.map(d => d.nCube);
    const cbrtValues = tableData.map(d => d.nCubeRoot);

    html += `<p style="margin-top: 20px;"><strong>Statistics:</strong></p>
        <pre>N: min=${Math.min(...nValues)}, max=${Math.max(...nValues)}, avg=${(nValues.reduce((a,b)=>a+b)/nValues.length).toFixed(2)}
N²: min=${Math.min(...n2Values)}, max=${Math.max(...n2Values)}, avg=${(n2Values.reduce((a,b)=>a+b)/n2Values.length).toFixed(2)}
√N: min=${Math.min(...sqrtValues).toFixed(4)}, max=${Math.max(...sqrtValues).toFixed(4)}, avg=${(sqrtValues.reduce((a,b)=>a+b)/sqrtValues.length).toFixed(4)}
N³: min=${Math.min(...n3Values)}, max=${Math.max(...n3Values)}, avg=${(n3Values.reduce((a,b)=>a+b)/n3Values.length).toFixed(2)}
∛N: min=${Math.min(...cbrtValues).toFixed(4)}, max=${Math.max(...cbrtValues).toFixed(4)}, avg=${(cbrtValues.reduce((a,b)=>a+b)/cbrtValues.length).toFixed(4)}</pre>`;

    showResult('table-result', html, 'success');
}

// ============== Q5: Array and String Operations ==============

function generateQ5ArrayInput() {
    const size = parseInt(document.getElementById('q5-array-size').value);
    if (!size || size < 1 || size > 100) {
        showResult('q5-array-result', 'Please enter a valid size (1-100)', 'error');
        return;
    }

    const container = document.getElementById('q5-array-inputs');
    container.innerHTML = '';
    
    for (let i = 0; i < size; i++) {
        const input = document.createElement('input');
        input.type = 'number';
        input.id = `q5-arr-${i}`;
        input.placeholder = `[${i}]`;
        input.step = '0.01';
        container.appendChild(input);
    }
}

function calculateQ5EvenIndexSum() {
    const size = parseInt(document.getElementById('q5-array-size').value);
    if (!size || size < 1) {
        showResult('q5-array-result', 'Please generate input fields first', 'error');
        return;
    }

    const arr = [];
    for (let i = 0; i < size; i++) {
        const val = parseFloat(document.getElementById(`q5-arr-${i}`).value);
        if (isNaN(val)) {
            showResult('q5-array-result', 'Please enter valid numbers in all fields', 'error');
            return;
        }
        arr.push(val);
    }

    let evenSum = 0;
    let evenIndices = [];
    
    for (let i = 2; i < arr.length; i += 2) {
        evenSum += arr[i];
        evenIndices.push(`arr[${i}] = ${arr[i]}`);
    }

    let html = `<h4>Even Index Elements Sum</h4>
        <p><strong>Array:</strong> [${arr.join(', ')}]</p>
        <p><strong>Even Indices (2, 4, 6...):</strong></p>
        <pre>${evenIndices.join('\n')}</pre>
        <p><strong>Sum of even index elements:</strong> ${evenSum}</p>`;
    
    showResult('q5-array-result', html, 'success');
}

function searchWord() {
    const text = document.getElementById('q5-text').value.trim();
    const word = document.getElementById('q5-word').value.trim().toLowerCase();

    if (!text || !word) {
        showResult('q5-word-result', 'Please enter both text and word', 'error');
        return;
    }

    // Extract words using regex
    const words = text.toLowerCase().match(/\b[a-z]+\b/g) || [];
    const count = words.filter(w => w === word).length;

    // Word frequency analysis
    const frequency = {};
    words.forEach(w => {
        frequency[w] = (frequency[w] || 0) + 1;
    });

    const sortedFreq = Object.entries(frequency)
        .sort((a, b) => b[1] - a[1])
        .slice(0, 10);

    let html = `<h4>Word Search Results</h4>
        <p><strong>Search Word:</strong> "${word}"</p>
        <p><strong>Occurrences:</strong> <span style="color: #667eea; font-size: 1.3em; font-weight: bold;">${count}</span></p>
        <p><strong>Total Unique Words:</strong> ${Object.keys(frequency).length}</p>
        <p><strong>Top 10 Most Frequent Words:</strong></p>
        <table>
            <tr><th>Word</th><th>Frequency</th></tr>
            ${sortedFreq.map(([w, f]) => `<tr><td>${w}</td><td>${f}</td></tr>`).join('')}
        </table>`;
    
    showResult('q5-word-result', html, 'success');
}

// ============== Q6: Age and Quadratic ==============

function classifyAge() {
    const age = parseInt(document.getElementById('q6-age').value);
    
    if (isNaN(age) || age < 0 || age > 150) {
        showResult('q6-age-result', 'Please enter a valid age (0-150)', 'error');
        return;
    }

    let category = '';
    let description = '';

    if (age < 5) {
        category = 'Early Childhood';
        description = 'Ages 0-4: Pre-school age';
    } else if (age < 13) {
        category = 'Child';
        description = 'Ages 5-12: School-age children';
    } else if (age < 18) {
        category = 'Teenager';
        description = 'Ages 13-17: Adolescence';
    } else if (age < 65) {
        category = 'Adult';
        description = 'Ages 18-64: Working age';
    } else {
        category = 'Senior';
        description = 'Ages 65+: Retirement age';
    }

    let html = `<h4>Age Classification</h4>
        <p><strong>Age:</strong> ${age} years</p>
        <p><strong>Category:</strong> <span style="color: #667eea; font-weight: bold; font-size: 1.2em;">${category}</span></p>
        <p><strong>Description:</strong> ${description}</p>`;
    
    showResult('q6-age-result', html, 'success');
}

function solveQuadraticQ6() {
    const a = parseFloat(document.getElementById('q6-a').value);
    const b = parseFloat(document.getElementById('q6-b').value);
    const c = parseFloat(document.getElementById('q6-c').value);

    if (isNaN(a) || isNaN(b) || isNaN(c) || a === 0) {
        showResult('q6-quad-result', 'Please enter valid coefficients (a cannot be zero)', 'error');
        return;
    }

    const discriminant = b * b - 4 * a * c;
    let html = `<h4>Quadratic Equation: ${a}x² + ${b}x + ${c} = 0</h4>
        <p><strong>Discriminant (Δ):</strong> ${b}² - 4(${a})(${c}) = ${discriminant.toFixed(4)}</p>`;

    if (discriminant > 0) {
        const root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        const root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
        html += `<p><strong>Two Distinct Real Roots:</strong></p>
            <p>x₁ = ${root1.toFixed(4)}</p>
            <p>x₂ = ${root2.toFixed(4)}</p>`;
    } else if (discriminant === 0) {
        const root = -b / (2 * a);
        html += `<p><strong>One Repeated Root:</strong></p>
            <p>x = ${root.toFixed(4)}</p>`;
    } else {
        const realPart = (-b / (2 * a)).toFixed(4);
        const imagPart = (Math.sqrt(-discriminant) / (2 * a)).toFixed(4);
        html += `<p><strong>Complex Roots:</strong></p>
            <p>x₁ = ${realPart} + ${imagPart}i</p>
            <p>x₂ = ${realPart} - ${imagPart}i</p>`;
    }

    showResult('q6-quad-result', html, 'success');
}

// ============== Q7: Quadratic Roots ==============

function solveQuadraticQ7() {
    const a = parseFloat(document.getElementById('q7-a').value);
    const b = parseFloat(document.getElementById('q7-b').value);
    const c = parseFloat(document.getElementById('q7-c').value);

    if (isNaN(a) || isNaN(b) || isNaN(c) || a === 0) {
        showResult('q7-result', 'Please enter valid coefficients (a cannot be zero)', 'error');
        return;
    }

    const discriminant = b * b - 4 * a * c;
    
    let html = `<h4>Solving: ${a}x² + (${b})x + ${c} = 0</h4>
        <p><strong>Using formula:</strong> x = (-b ± √Δ) / 2a</p>
        <p><strong>Discriminant (Δ):</strong> b² - 4ac = ${discriminant.toFixed(4)}</p>`;

    if (discriminant > 0) {
        const sqrtDelta = Math.sqrt(discriminant);
        const root1 = (-b + sqrtDelta) / (2 * a);
        const root2 = (-b - sqrtDelta) / (2 * a);
        
        html += `<p><strong>Status:</strong> Two Distinct Real Roots</p>
            <p><strong>√Δ:</strong> ${sqrtDelta.toFixed(4)}</p>
            <p><strong>Root 1 (x₁):</strong> (-${b} + ${sqrtDelta.toFixed(4)}) / ${2*a} = ${root1.toFixed(4)}</p>
            <p><strong>Root 2 (x₂):</strong> (-${b} - ${sqrtDelta.toFixed(4)}) / ${2*a} = ${root2.toFixed(4)}</p>
            <p><strong>Verification:</strong></p>
            <p>x₁ check: ${(a * root1 * root1 + b * root1 + c).toFixed(6)} ≈ 0 ✓</p>
            <p>x₂ check: ${(a * root2 * root2 + b * root2 + c).toFixed(6)} ≈ 0 ✓</p>`;
    } else if (discriminant === 0) {
        const root = -b / (2 * a);
        html += `<p><strong>Status:</strong> One Repeated Root</p>
            <p><strong>Root (x):</strong> ${root.toFixed(4)}</p>`;
    } else {
        const realPart = -b / (2 * a);
        const imagPart = Math.sqrt(-discriminant) / (2 * a);
        html += `<p><strong>Status:</strong> Complex Roots</p>
            <p><strong>Root 1 (x₁):</strong> ${realPart.toFixed(4)} + ${imagPart.toFixed(4)}i</p>
            <p><strong>Root 2 (x₂):</strong> ${realPart.toFixed(4)} - ${imagPart.toFixed(4)}i</p>`;
    }

    showResult('q7-result', html, 'success');
}

// ============== Q8: Operators and Arithmetic ==============

function showOperatorExamples() {
    let html = `<h4>Operator Examples</h4>
        <p><strong>1. Line Comment (//):</strong> Ignores everything after //</p>
        <pre>int x = 5; // This is a comment</pre>
        
        <p><strong>2. Logical OR (||):</strong> Returns true if at least one condition is true</p>
        <table>
            <tr><th>A</th><th>B</th><th>A || B</th></tr>
            <tr><td>true</td><td>true</td><td>true</td></tr>
            <tr><td>true</td><td>false</td><td>true</td></tr>
            <tr><td>false</td><td>true</td><td>true</td></tr>
            <tr><td>false</td><td>false</td><td>false</td></tr>
        </table>
        
        <p><strong>3. Logical AND (&&):</strong> Returns true only if both conditions are true</p>
        <table>
            <tr><th>A</th><th>B</th><th>A && B</th></tr>
            <tr><td>true</td><td>true</td><td>true</td></tr>
            <tr><td>true</td><td>false</td><td>false</td></tr>
            <tr><td>false</td><td>true</td><td>false</td></tr>
            <tr><td>false</td><td>false</td><td>false</td></tr>
        </table>
        
        <p><strong>4. Bitwise XOR (^):</strong> XOR comparison on bits</p>
        <pre>5 ^ 3 = 0101 ^ 0011 = 0110 = 6</pre>
        
        <p><strong>5. Tab Character (\\t):</strong> Inserts horizontal tab space</p>
        <pre>System.out.println("Name\\tAge\\tCity");</pre>`;
    
    showResult('q8-operators', html, 'info');
}

function demonstrateArithmetic() {
    const num = parseFloat(document.getElementById('q8-numerator').value);
    const denom = parseFloat(document.getElementById('q8-denominator').value);

    if (isNaN(num) || isNaN(denom) || denom === 0) {
        showResult('q8-arithmetic', 'Please enter valid numbers (denominator cannot be zero)', 'error');
        return;
    }

    const realDiv = num / denom;
    const intDiv = Math.floor(num / denom);
    const modulo = num % denom;

    let html = `<h4>Arithmetic Operations</h4>
        <p><strong>Numerator:</strong> ${num}</p>
        <p><strong>Denominator:</strong> ${denom}</p>
        <p><strong>Real Division (/):</strong> ${num} / ${denom} = ${realDiv.toFixed(4)}</p>
        <p><strong>Integer Division (//):</strong> ${num} // ${denom} = ${intDiv}</p>
        <p><strong>Modulo (%):</strong> ${num} % ${denom} = ${modulo.toFixed(4)}</p>`;
    
    showResult('q8-arithmetic', html, 'success');
}

function showCodeAnalysis() {
    let html = `<h4>Code Analysis: Original vs Corrected</h4>
        <p><strong>Original Code (with errors):</strong></p>
        <pre>int sum = 0;
for (int i = 10; i >= 2; i--)
    for (int j = 2; j <= i; j++);  // ERROR 1: Unnecessary semicolon!
        sum += i * j;  // ERROR 2: Never executes due to semicolon
        
if (sum > 50)
System.out.println("Sum is large");  // ERROR 3: Missing braces for clarity</pre>

        <p><strong>Issues Found:</strong></p>
        <ol>
            <li><strong>Syntax Error - Line 3:</strong> Semicolon after for loop body makes inner loop do nothing</li>
            <li><strong>Logic Error - Line 4:</strong> sum += statement never executes</li>
            <li><strong>Style Issue - Line 7:</strong> Missing braces for readability</li>
        </ol>

        <p><strong>Corrected Code:</strong></p>
        <pre>int sum = 0;
for (int i = 10; i >= 2; i--)
    for (int j = 2; j <= i; j++)  // Semicolon removed
        sum += i * j;  // Now executes properly
        
if (sum > 50) {
    System.out.println("Sum is large");  // Better formatting
}</pre>

        <p><strong>Execution Trace:</strong></p>
        <pre>i=10: j=2,3,...,10 → sum += 10*(2+3+...+10) = 10*54 = 540
i=9:  j=2,3,...,9  → sum += 9*(2+3+...+9) = 9*44 = 396
i=8:  j=2,3,...,8  → sum += 8*(2+3+...+8) = 8*35 = 280
...continues...
Final sum = 1584 (much larger than 50)</pre>`;
    
    showResult('q8-code', html, 'info');
}

// ============== Q9: For Loops and Mean ==============

function showForLoopExamples() {
    let html = `<h4>Four Types of For Loops</h4>
        
        <p><strong>1. Up-Counting For Loop:</strong></p>
        <pre>for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
Output: 1 2 3 4 5</pre>
        
        <p><strong>2. Down-Counting For Loop:</strong></p>
        <pre>for (int i = 5; i >= 1; i--) {
    System.out.println(i);
}
Output: 5 4 3 2 1</pre>
        
        <p><strong>3. Nested For Loop:</strong></p>
        <pre>for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.print(i * j + " ");
    }
    System.out.println();
}
Output: 1 2 3
        2 4 6
        3 6 9</pre>
        
        <p><strong>4. Infinite For Loop:</strong></p>
        <pre>for (;;) {  // No initialization, condition, or increment
    System.out.println("This runs forever!");
    if (condition) break;  // Must use break to exit
}</pre>`;
    
    showResult('q9-loops', html, 'info');
}

function generateQ9ArrayInput() {
    const size = parseInt(document.getElementById('q9-array-size').value);
    if (!size || size < 1 || size > 100) {
        showResult('q9-stats-result', 'Please enter a valid size (1-100)', 'error');
        return;
    }

    const container = document.getElementById('q9-array-inputs');
    container.innerHTML = '';
    
    for (let i = 0; i < size; i++) {
        const input = document.createElement('input');
        input.type = 'number';
        input.id = `q9-arr-${i}`;
        input.placeholder = `[${i}]`;
        input.step = '0.01';
        container.appendChild(input);
    }
}

function calculateQ9Statistics() {
    const size = parseInt(document.getElementById('q9-array-size').value);
    if (!size || size < 1) {
        showResult('q9-stats-result', 'Please generate input fields first', 'error');
        return;
    }

    const arr = [];
    for (let i = 0; i < size; i++) {
        const val = parseFloat(document.getElementById(`q9-arr-${i}`).value);
        if (isNaN(val)) {
            showResult('q9-stats-result', 'Please enter valid numbers in all fields', 'error');
            return;
        }
        arr.push(val);
    }

    // Calculate statistics
    const mean = arr.reduce((a, b) => a + b, 0) / arr.length;
    
    const sorted = [...arr].sort((a, b) => a - b);
    const median = arr.length % 2 === 0 
        ? (sorted[arr.length / 2 - 1] + sorted[arr.length / 2]) / 2 
        : sorted[Math.floor(arr.length / 2)];
    
    const variance = arr.reduce((sum, val) => sum + Math.pow(val - mean, 2), 0) / arr.length;
    const stdDev = Math.sqrt(variance);
    
    const min = Math.min(...arr);
    const max = Math.max(...arr);
    const range = max - min;

    let html = `<h4>Statistical Analysis</h4>
        <p><strong>Array:</strong> [${arr.join(', ')}]</p>
        <p><strong>Count:</strong> ${arr.length}</p>
        <p><strong>Mean (Average):</strong> ${mean.toFixed(4)}</p>
        <p><strong>Median (Middle Value):</strong> ${median.toFixed(4)}</p>
        <p><strong>Standard Deviation:</strong> ${stdDev.toFixed(4)}</p>
        <p><strong>Minimum:</strong> ${min.toFixed(4)}</p>
        <p><strong>Maximum:</strong> ${max.toFixed(4)}</p>
        <p><strong>Range:</strong> ${range.toFixed(4)}</p>`;
    
    showResult('q9-stats-result', html, 'success');
}

// ============== Q10: Weather Station Analysis ==============

function generateQ10TemperatureInput() {
    const stations = parseInt(document.getElementById('q10-stations').value);
    const readings = parseInt(document.getElementById('q10-readings').value);

    if (!stations || !readings || stations < 1 || readings < 1) {
        showResult('q10-weather-result', 'Please enter valid station and reading counts', 'error');
        return;
    }

    const container = document.getElementById('q10-temp-inputs');
    container.innerHTML = '';
    
    for (let i = 0; i < stations; i++) {
        const stationLabel = document.createElement('div');
        stationLabel.style.cssText = 'grid-column: 1 / -1; font-weight: bold; margin-top: 10px; color: #667eea;';
        stationLabel.textContent = `Station ${i + 1}`;
        container.appendChild(stationLabel);
        
        for (let j = 0; j < readings; j++) {
            const input = document.createElement('input');
            input.type = 'number';
            input.id = `q10-temp-${i}-${j}`;
            input.placeholder = `Reading ${j + 1}`;
            input.step = '0.1';
            container.appendChild(input);
        }
    }
}

function analyzeWeatherData() {
    const stations = parseInt(document.getElementById('q10-stations').value);
    const readings = parseInt(document.getElementById('q10-readings').value);

    if (!stations || !readings) {
        showResult('q10-weather-result', 'Please generate input fields first', 'error');
        return;
    }

    const tempData = [];
    for (let i = 0; i < stations; i++) {
        const row = [];
        for (let j = 0; j < readings; j++) {
            const val = parseFloat(document.getElementById(`q10-temp-${i}-${j}`).value);
            if (isNaN(val)) {
                showResult('q10-weather-result', 'Please enter valid temperatures in all fields', 'error');
                return;
            }
            row.push(val);
        }
        tempData.push(row);
    }

    // Calculate statistics
    const stationMeans = tempData.map(station => {
        const sum = station.reduce((a, b) => a + b, 0);
        return sum / station.length;
    });

    const overallMean = stationMeans.reduce((a, b) => a + b, 0) / stationMeans.length;

    let allTemps = [];
    tempData.forEach(station => allTemps.push(...station));
    const hottest = Math.max(...allTemps);
    const coldest = Math.min(...allTemps);

    let html = `<h4>Weather Station Analysis</h4>
        <p><strong>Total Stations:</strong> ${stations}</p>
        <p><strong>Readings per Station:</strong> ${readings}</p>
        <p><strong>Overall Mean Temperature:</strong> ${overallMean.toFixed(2)}°C</p>
        <p><strong>Hottest Temperature:</strong> ${hottest.toFixed(2)}°C</p>
        <p><strong>Coldest Temperature:</strong> ${coldest.toFixed(2)}°C</p>
        
        <p><strong>Station Mean Temperatures:</strong></p>
        <table>
            <tr><th>Station</th><th>Mean Temp (°C)</th></tr>
            ${stationMeans.map((mean, idx) => 
                `<tr><td>Station ${idx + 1}</td><td>${mean.toFixed(2)}</td></tr>`
            ).join('')}
        </table>
        
        <p><strong>Station Readings:</strong></p>
        <table>
            <tr><th>Station</th>${Array.from({length: readings}, (_, i) => 
                `<th>Reading ${i + 1}</th>`).join('')}</tr>
            ${tempData.map((station, idx) => 
                `<tr><td>Station ${idx + 1}</td>${station.map(temp => 
                    `<td>${temp.toFixed(1)}</td>`).join('')}</tr>`
            ).join('')}
        </table>`;
    
    showResult('q10-weather-result', html, 'success');
}

// ============== Q11: Employee Biodata ==============

function registerEmployee() {
    const firstName = document.getElementById('q11-fname').value.trim();
    const lastName = document.getElementById('q11-lname').value.trim();
    const yearOfBirth = parseInt(document.getElementById('q11-dob').value);
    const nextOfKin = document.getElementById('q11-kin').value.trim();
    const address = document.getElementById('q11-address').value.trim();

    if (!firstName || !lastName || !yearOfBirth || !nextOfKin || !address) {
        showResult('q11-register-result', 'Please fill in all fields', 'error');
        return;
    }

    if (yearOfBirth < 1950 || yearOfBirth > 2010) {
        showResult('q11-register-result', 'Year of birth must be between 1950 and 2010', 'error');
        return;
    }

    const currentYear = 2026;
    const age = currentYear - yearOfBirth;
    const retirementYear = yearOfBirth + 65;

    const GROSS_PAY = 100000;
    const TAX_RATE = 0.125;
    const PENSION_RATE = 0.075;
    const HEALTH_RATE = 0.05;
    const HOUSING_RATE = 0.05;

    const taxDeduction = GROSS_PAY * TAX_RATE;
    const pensionDeduction = GROSS_PAY * PENSION_RATE;
    const healthDeduction = GROSS_PAY * HEALTH_RATE;
    const housingDeduction = GROSS_PAY * HOUSING_RATE;

    const totalDeductions = taxDeduction + pensionDeduction + healthDeduction + housingDeduction;
    const netPay = GROSS_PAY - totalDeductions;

    const employee = {
        firstName,
        lastName,
        yearOfBirth,
        age,
        nextOfKin,
        address,
        retirementYear,
        grossPay: GROSS_PAY,
        taxDeduction,
        pensionDeduction,
        healthDeduction,
        housingDeduction,
        netPay
    };

    employeeRegistry.push(employee);

    let html = `<h4>✓ Employee Registered Successfully</h4>
        <p><strong>Name:</strong> ${firstName} ${lastName}</p>
        <p><strong>Age:</strong> ${age} years</p>
        <p><strong>Retirement Year:</strong> ${retirementYear}</p>
        <p><strong>Gross Pay:</strong> ₦${GROSS_PAY.toLocaleString()}</p>
        <p><strong>Net Pay:</strong> ₦${netPay.toLocaleString()}</p>`;
    
    showResult('q11-register-result', html, 'success');

    // Clear form
    document.getElementById('q11-fname').value = '';
    document.getElementById('q11-lname').value = '';
    document.getElementById('q11-dob').value = '';
    document.getElementById('q11-kin').value = '';
    document.getElementById('q11-address').value = '';
}

function displayEmployeeRegistry() {
    if (employeeRegistry.length === 0) {
        showResult('q11-registry', 'No employees registered yet', 'info');
        return;
    }

    let html = `<h4>Employee Registry (${employeeRegistry.length} employees)</h4>`;
    
    employeeRegistry.forEach((emp, idx) => {
        html += `<div class="employee-card">
            <h5>Employee #${idx + 1}: ${emp.firstName} ${emp.lastName}</h5>
            <div class="info-row">
                <span class="label">Date of Birth:</span>
                <span class="value">${emp.yearOfBirth}</span>
            </div>
            <div class="info-row">
                <span class="label">Age:</span>
                <span class="value">${emp.age} years</span>
            </div>
            <div class="info-row">
                <span class="label">Next of Kin:</span>
                <span class="value">${emp.nextOfKin}</span>
            </div>
            <div class="info-row">
                <span class="label">Address:</span>
                <span class="value">${emp.address}</span>
            </div>
            <div class="info-row">
                <span class="label">Retirement Year:</span>
                <span class="value">${emp.retirementYear}</span>
            </div>
            <div class="info-row">
                <span class="label">Gross Pay:</span>
                <span class="value">₦${emp.grossPay.toLocaleString()}</span>
            </div>
            <div class="info-row">
                <span class="label">Net Pay:</span>
                <span class="value">₦${emp.netPay.toLocaleString()}</span>
            </div>
        </div>`;
    });
    
    showResult('q11-registry', html, 'success');
}

function displayPayrollSummary() {
    if (employeeRegistry.length === 0) {
        showResult('q11-payroll', 'No employees registered yet', 'info');
        return;
    }

    const totalGross = employeeRegistry.reduce((sum, emp) => sum + emp.grossPay, 0);
    const totalDeductions = employeeRegistry.reduce((sum, emp) => 
        sum + (emp.taxDeduction + emp.pensionDeduction + emp.healthDeduction + emp.housingDeduction), 0);
    const totalNet = employeeRegistry.reduce((sum, emp) => sum + emp.netPay, 0);

    let html = `<h4>Payroll Summary</h4>
        <p><strong>Total Employees:</strong> ${employeeRegistry.length}</p>
        <p><strong>Total Gross Pay:</strong> ₦${totalGross.toLocaleString()}</p>
        <p><strong>Total Deductions:</strong> ₦${totalDeductions.toLocaleString()}</p>
        <p><strong>Total Net Pay:</strong> ₦${totalNet.toLocaleString()}</p>
        
        <p><strong>Breakdown:</strong></p>
        <table>
            <tr><th>Employee</th><th>Gross (₦)</th><th>Tax</th><th>Pension</th><th>Health</th><th>Housing</th><th>Net (₦)</th></tr>
            ${employeeRegistry.map(emp => `
                <tr>
                    <td>${emp.firstName} ${emp.lastName}</td>
                    <td>${emp.grossPay.toLocaleString()}</td>
                    <td>${emp.taxDeduction.toLocaleString()}</td>
                    <td>${emp.pensionDeduction.toLocaleString()}</td>
                    <td>${emp.healthDeduction.toLocaleString()}</td>
                    <td>${emp.housingDeduction.toLocaleString()}</td>
                    <td>${emp.netPay.toLocaleString()}</td>
                </tr>
            `).join('')}
        </table>`;
    
    showResult('q11-payroll', html, 'success');
}

// ============== Utility Functions ==============

function showResult(elementId, content, type = 'info') {
    const element = document.getElementById(elementId);
    element.innerHTML = content;
    element.className = `result result-${type}`;
}
