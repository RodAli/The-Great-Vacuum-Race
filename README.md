# The-Great-Vacuum-Race
A simple 2-D game where you collect dustballs for CSC207 Software Design assignment

#### Rules
The game board is a 2-dimensional grid. Each cell in this grid represents either a section of hallway, a piece of wall, or a dumpster. We denote a wall with the symbol X, a hallway with a blank space, and dumpster with U. There are two players in this game | two vacuums. We denote them with symbols 1 and 2. Some of the cells are dirty: they contain dirt (.) or dustballs (o). The vacuums' objective is to clean up as many dirty cells as possible. The dirt is stationary, but the dustballs move about the grid and each cell that a dustball visits becomes dirty (if it wasn't already) when the dustball leaves.
Each time a vacuum cleans dirt, the vacuum's score is incremented. In our implementation, dustballs are worth more. Each vacuum has a capacity. Cleaning up dirt or a dustball adds a constant amount to the fullness of the vacuum. When a vacuum becomes full, it cannot clean any more dirt; it can still go to dirty hallways, but it has no effect on the dirt that is there. A vacuum that enters a cell with a dumpster is emptied (i.e., it has zero fullness); thus, if a vacuum is full, in order to resume cleaning dirt, it must visit a dumpster. Dumpsters have no limit on their capacity.

The game ends when all dirt (including dustballs) is gone. The vacuum with the higher score wins, or, if the two scores are equal, we declare a tie.
