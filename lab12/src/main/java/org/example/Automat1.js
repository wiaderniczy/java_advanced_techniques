function nextGeneration(currentGeneration) {
    const newGeneration = new Array(currentGeneration.length).fill(0).map(() => new Array(currentGeneration.length).fill(0));
    for (var i = 1; i < currentGeneration.length - 1; i++) {
        for (var j = 1; j < currentGeneration[0].length - 1; j++) {
            newGeneration[i][j] = rule(currentGeneration[i][j], currentGeneration[i - 1][j], currentGeneration[i + 1][j], currentGeneration[i][j + 1], currentGeneration[i][j - 1]);
        }
    }
    return newGeneration;
}

function rule(a, b, c, d, e) {
   var x = a + b + c + d + e;
   if (x > 0 && x <= 2){
       return 1;
   }
   else return 0;
}
