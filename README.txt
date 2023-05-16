LINK GITHUB: https://github.com/rauldmj3/Eleccions_Java

Hemos quitado de la interficie el método all() ya que nosotros empezamos directamente importando cada tabla, en un HashMap.
Y eso nos permite leer más rápidamente las tablas y poder mostrarlas más rápido porque no tenemos que ir consultando a la base de datos.

Hemos quitado el count() de la interficie, al no tener variables diferentes según la clase en la que se crea pensamos que con poner solo una
en el que te pida el código de la tabla ya es suficiente.

Había un problema en la base de datos en relación con las tablas VotsCandidaturesMunicipis y VotsCandidaturesProvincies el cual hace que pete, ya que
hay en algunos casos que la provincia_id por ejemplo es 0, lo cual no puede ser porque no hay ninguna provincia_id que sea 0, y eso pasaba con el provincia_id de la tabla VotsCandidaturesProvincies
y la candidatura_id de la tabla de VotsCandidaturesMunicipis. Para solucionarlo hemos puesto un if en la importación de ambas tablas que si algún campo de los ya mencionados es 0, pase
al siguiente registro. Y en el caso de la tabla de VotsCandidaturesMunicipis también hay candidatura_id que tampoco están en la tabla candidatura_id.

Aunque en el GitHub ponga el nombre de uno de los dos, los dos hemos intervenido en parte del código para ir mejorándolo o solucionando problemas.
