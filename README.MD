# Test task at CYNTEKA

[Job vacancy](https://hh.ru/applicant/vacancy_response?vacancyId=77533338&hhtmFrom=vacancy)

## Exercise
```
Необходимо написать консольное приложение на Java(главный класс называть Main), 
в которое читает из файла input.txt входные данные:
n - число
далее n строк
m - число
далее m строк

Пример 1:
input.txt:
4
гвоздь
шуруп
краска синяя
ведро для воды
3
краска
корыто для воды
шуруп 3х1.5

ouput.txt:
гвоздь:?
шуруп:шуруп 3х1.5
краска синяя:краска
ведро для воды:корыто для воды


Пример 2:
1
Бетон с присадкой
1
Цемент

ouput.txt:
Бетон с присадкой:Цемент


Пример 3:
1
Бетон с присадкой
2
присадка для бетона
доставка

ouput.txt:
Бетон с присадкой:присадка бля бетона
доставка:?

Программа должна сопоставить максимально похожие строки из первого множества 
со строками из второго множества (одна к одной) и вывести результат в файл output.txt.
```


## Prerequisites

- Java 17 or higher
- Maven (for building the project)

## Configuration
Application settings can be found in `src/main/resources/application.yml`:

```yaml
app:
  files:
    input: "input.txt"
    output: "output.txt"
  match-algorithm:
    placeholder: "?"
```

`files.input`: Path to the input file containing the arrays of strings.  
`files.output`: Path to the output file where the matching pairs will be written.  
`match-algorithm.placeholder`: Placeholder used in the matching algorithm.  

## Building the project
```bash
mvn clean package
```

## Running the application
```bash
java -jar target/test-0.0.1-SNAPSHOT.jar
```

## Technologies Used
* Java 17
* Spring Boot
* Maven
* JUnit 5
* Lombok


## Solution Overview
This project uses two main algorithms to find the matching pairs of strings:
- **Longest Common Subsequence (LCS) Algorithm**: This algorithm is applied to compare 
  strings and find the longest common subsequence between them. It is used in the 
  `LCSDiffAlgorithm` class, which implements the `DiffAlgorithm` interface.

- **Greedy Matching Algorithm**: This algorithm is utilized to match pairs of strings 
  based on the calculated similarity scores using the LCS algorithm. 
  The greedy matching algorithm selects the highest scoring pair at each step and 
  removes them from the pool of remaining strings, iterating until no more 
  pairs can be matched. It is implemented in the `GreedyMatchAlgorithm` class, 
  which implements the `MatchAlgorithm` interface.

These two algorithms work together to efficiently find the matching pairs of strings 
from the input file and writing the results to the output file.

