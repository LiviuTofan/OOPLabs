matrix = []

# Input sudoku
for i in range(9):
    row = input()
    row_list = [int(x) for x in row.split()]
    matrix.append(row_list)

def row():
    global change
    change = 0
    for i in range(9):
        miss = 0
        index = 0
        sum = 0
        for j in range(9):
            sum += matrix[i][j]
            if matrix[i][j] == 0:
                miss += 1
                index = j
        if miss == 1:
            matrix[i][index] = 45 - sum
            change = 1

def column():
    global change
    change = 0
    for i in range(9):
        miss = 0
        index = 0
        sum = 0
        for j in range(9):
            sum += matrix[j][i]
            if matrix[j][i] == 0:
                miss += 1
                index = j
        if miss == 1:
            matrix[index][i] = 45 - sum
            change = 1

while True:
    row()
    column()
    if change != 1:
        break

for row in matrix:
    print(row)

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age
        afeaefeaf