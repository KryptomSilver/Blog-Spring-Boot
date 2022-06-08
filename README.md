# Blog-Spring-Boot
REST API con Spring Boot y buenas practicas
La REST API se usuaron validaciones y manejo de excepciones personalizadas, tambien se hizo una paginación para las publicaciones.
## Requisitos:
- Mysql
- Spring Boot IDE
- Postman (Manejo de peticiones)
- JAVA

## Endpoints:
#### Usuarios
##### Registrar un usuario (POST) "/api/auth/registrar"
Cuerpo para la petición
```json
{
    "nombre":"usuario",
    "username":"usuario",
    "password":"123456",
    "email":"correo@gmail.com"
}
```
##### Iniciar sesión para obtener un token (POST) "/api/auth/iniciarSesion"
Cuerpo para la petición:
```json
{
    "usernameOrEmail":"usuario",
    "password":"123456"
}
```
Ejemplo respuesta:
```json
{
    "tokeDeAcceso": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag",
    "tipoDeToken": "Bearer"
}
```
#### Publicación
##### Crear de publicación (POST) "/api/publicacion":
Se necesita un JWT de un administrador para poder crear la publicación
Ejemplo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag
```
Cuerpo para la petición
```json
{
    "titulo":"Publicacion 1",
    "descripcion":"Descripción de la publicación",
    "contenido":"Contenido de la publicación"
}
```
##### Listar publicaciones (GET) "/api/publicacion":
Al url se le pueden agregar queries las cuales pueden permitir:
Ordernar el contenido de manera acendente (asc) y desendente (desc)
**"/api/publicaciones?sortDir=desc"**
De esta manera se ordenan descendente por el ID
```json
{
    "contenido": [
        {
            "id": 11,
            "titulo": "1",
            "descripcion": "a",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 10,
            "titulo": "7 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 9,
            "titulo": "6 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 8,
            "titulo": "5 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 7,
            "titulo": "4 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 6,
            "titulo": "3 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 5,
            "titulo": "2 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
    ],
    "numeroPagina": 0,
    "medidaPagina": 10,
    "totalElementos": 10,
    "totalPaginas": 1,
    "ultima": true
}
```
Ordernar el contenido por un campo en especifico
**"/api/publicaciones?sortDir=desc&sortBy=titulo"**
De esta manera se ordenan descendente por el ID
```json
{
    "contenido": [
        {
            "id": 10,
            "titulo": "7 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 9,
            "titulo": "6 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 8,
            "titulo": "5 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 7,
            "titulo": "4 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 6,
            "titulo": "3 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 15,
            "titulo": "2 publicacion",
            "descripcion": "descripcion",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
    ],
    "numeroPagina": 0,
    "medidaPagina": 10,
    "totalElementos": 10,
    "totalPaginas": 1,
    "ultima": true
}
```
Ordernar el contenido por pagina y el tamaño de la pagina (No publicaciones por pagina)
**"/api/publicaciones?sortDir=desc&pagNo=0&pagSize=3"**
Con estas dos queries podemos apreciar que nos devuelve solo 3 publicaciones y esta en la primera pagina.
```json
{
    "contenido": [
        {
            "id": 4,
            "titulo": "1 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": [
                {
                    "id": 4,
                    "nombre": "comentariox2",
                    "email": "correo@gmail.com",
                    "cuerpo": "texto comentariox",
                    "publicacion": {
                        "id": 4,
                        "titulo": "1 publicacion",
                        "descripcion": "Segunda",
                        "contenido": "hola soy una publicacion 2"
                    }
                },
                {
                    "id": 2,
                    "nombre": "Actualizado",
                    "email": "correo@gmail.com",
                    "cuerpo": "texto comentariox",
                    "publicacion": {
                        "id": 4,
                        "titulo": "1 publicacion",
                        "descripcion": "Segunda",
                        "contenido": "hola soy una publicacion 2"
                    }
                },
                {
                    "id": 3,
                    "nombre": "comentariox2",
                    "email": "correo@gmail.com",
                    "cuerpo": "texto comentariox",
                    "publicacion": {
                        "id": 4,
                        "titulo": "1 publicacion",
                        "descripcion": "Segunda",
                        "contenido": "hola soy una publicacion 2"
                    }
                }
            ]
        },
        {
            "id": 5,
            "titulo": "2 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 6,
            "titulo": "3 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 7,
            "titulo": "4 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 8,
            "titulo": "5 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 9,
            "titulo": "6 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 10,
            "titulo": "7 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 11,
            "titulo": "1",
            "descripcion": "a",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 14,
            "titulo": "21",
            "descripcion": "a",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
        {
            "id": 15,
            "titulo": "22",
            "descripcion": "descripción",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        }
    ],
    "numeroPagina": 0,
    "medidaPagina": 10,
    "totalElementos": 10,
    "totalPaginas": 1,
    "ultima": true
}
```
Ejemplo de respuesta en JSON:
```json
{
    "contenido": [
        {
            "id": 4,
            "titulo": "1 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": [
                {
                    "id": 4,
                    "nombre": "comentariox2",
                    "email": "correo@gmail.com",
                    "cuerpo": "texto comentariox",
                    "publicacion": {
                        "id": 4,
                        "titulo": "1 publicacion",
                        "descripcion": "Segunda",
                        "contenido": "hola soy una publicacion 2"
                    }
                },
                {
                    "id": 3,
                    "nombre": "comentariox2",
                    "email": "correo@gmail.com",
                    "cuerpo": "texto comentariox",
                    "publicacion": {
                        "id": 4,
                        "titulo": "1 publicacion",
                        "descripcion": "Segunda",
                        "contenido": "hola soy una publicacion 2"
                    }
                },
                {
                    "id": 2,
                    "nombre": "Actualizado",
                    "email": "correo@gmail.com",
                    "cuerpo": "texto comentariox",
                    "publicacion": {
                        "id": 4,
                        "titulo": "1 publicacion",
                        "descripcion": "Segunda",
                        "contenido": "hola soy una publicacion 2"
                    }
                }
            ]
        },
        {
            "id": 10,
            "titulo": "7 publicacion",
            "descripcion": "Segunda",
            "contenido": "hola soy una publicacion 2",
            "comentarios": []
        },
    ],
    "numeroPagina": 0,
    "medidaPagina": 10,
    "totalElementos": 10,
    "totalPaginas": 1,
    "ultima": true
}
```
##### Obtener publicación por ID (GET)  "/api/publicacion/{id_publicacion}":
Se tiene que sustituir el nombre *id_publicacion* y poner el id de la publicación.
Ejemplo respuesta:
```json
{
    "id": 4,
    "titulo": "1 publicacion",
    "descripcion": "Segunda",
    "contenido": "hola soy una publicacion 2",
    "comentarios": [
        {
            "id": 3,
            "nombre": "comentariox2",
            "email": "correogmail.com",
            "cuerpo": "texto comentariox",
            "publicacion": {
                "id": 4,
                "titulo": "1 publicacion",
                "descripcion": "Segunda",
                "contenido": "hola soy una publicacion 2"
            }
        },
        {
            "id": 4,
            "nombre": "comentariox2",
            "email": "correo@gmail.com",
            "cuerpo": "texto comentariox",
            "publicacion": {
                "id": 4,
                "titulo": "1 publicacion",
                "descripcion": "Segunda",
                "contenido": "hola soy una publicacion 2"
            }
        },
        {
            "id": 2,
            "nombre": "Actualizado",
            "email": "correo@gmail.com",
            "cuerpo": "texto comentariox",
            "publicacion": {
                "id": 4,
                "titulo": "1 publicacion",
                "descripcion": "Segunda",
                "contenido": "hola soy una publicacion 2"
            }
        }
    ]
}
```
##### Editar publicación (PUT) "/api/publicacion/{id_publicacion}"
Se le tiene que proporcionar un JWT de un usuario administador.
Ejemplo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag
```
Cuerpo para la petición
```json
{
    "titulo":"Prueba actualización",
    "descripcion":"Segunda",
    "contenido":"ACTUALIZADO"
}
```
##### Eliminar publicación (DELETE) "/api/publicacion/{id_publicacion}"
Se le tiene que proporcionar un JWT de un usuario administrador
Ejemplo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag
```
#### Comentarios ("api/cometarios")
##### Crear comentario para una publicación (POST) "/api/publicaciones/{id_publicacion}/comentarios"
Se necesita un JWT de un administrador para poder crear el comentario y sustituir el *id_publicacion* por la publicación que se le asignara el comentario. 
Ejemplo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag
```
Cuerpo para la petición
```json
{
    "nombre":"Nombre del comentario",
    "cuerpo":"Cuerpo del comentario",
    "email":"usuario"
}
```
##### Obtener comentarios por publicación (GET) "/api/publicaciones/{id_publicacion}"
Solo se sustituirá el *id_publicacion* por el numero de la publicación deseada.
Ejemplo de respuesta en JSON:
```json
[
    {
        "id": 2,
        "nombre": "Actualizado",
        "email": "correo3@gmail.com",
        "cuerpo": "texto comentariox"
    },
    {
        "id": 3,
        "nombre": "comentariox2",
        "email": "correo63@gmail.com",
        "cuerpo": "texto comentariox"
    },
    {
        "id": 4,
        "nombre": "comentariox2",
        "email": "correo@gmail.com",
        "cuerpo": "texto comentariox"
    }
]
```
##### Obtener un unico comentario por id y por publicación (GET) "/api/publicacion/{id_publicacion}/comentarios/{id_comentario}"
Solo se sustituirá el *id_publicacion* por el numero de la publicación deseada y el *id_comentario* de igual manera.
Ejemplo de respuesta en JSON:
```json
{
    "id": 2,
    "nombre": "Actualizado",
    "email": "correo@gmail.com",
    "cuerpo": "texto comentariox"
}
```
##### Actualizar comentario por id (PUT) "/api/publicacion/{id_publicacion}/comentarios/{id_comentario}"
Solo se sustituirá el *id_publicacion* por el numero de la publicación deseada y el *id_comentario* de igual manera.
Se necesita un JWT de administrador.
Ejemplo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag
```
Cuerpo para la petición
```json
{
    "nombre": "Actualizado",
    "email": "correo@gmail.com",
    "cuerpo": "texto comentariox"
}
```
##### Eliminar comentario (DELETE) "/api/publicacion/{id_publicacion}/comentarios/{id_comentario}"
Se le tiene que proporcionar un JWT de un usuario administrador
Ejemplo:
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmVscm9tZXJvcjc2M0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MTA5OTUsImV4cCI6MTY1NTMxNTc5NX0.UNhnj83kAI2uD663v6wNN6jNaZnh68v1I4sZKt4Q0Ty_vA5RKb5Zkq_fHtoDPifFgdzHICgexvz3FOrGSidoag
```




