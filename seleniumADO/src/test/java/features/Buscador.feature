    @R2
    @Buscador
    Feature: Buscador sencillo
    Yo como usuario
    Quiero buscar paradas, lineas, sitios de interés de una manera sencilla
    Para acceder a la oferta Mobility ADO

  Rule: Al seleccionar el buscador sencillo, el usuario podrá ingresar el texto para acceder a la información que le interesa conocer y la información se mostrará acorde a los siguientes casos...

    @CP-US2-003
    Scenario Outline: Búsqueda iniciada con un carácter numérico
      Given El usuario abre la pagina
      When Ingresar un número como primer carácter "<numero>"
      Then Se muestra resultado coincidente con la busqueda

      Examples:
        |  numero  |
        |    4E    |
        |    2     |