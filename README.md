# catalog-api

catalog-api is a multi-module project. It is made up of:

- libraries-api-module
- items-api-module

catalog-api has the main configuration for the app, including all the security.

## Exceptions

| Exception | Code | Message | Class | Notes |
|:--------- |:----:|:------- |:----- |:----- |
| Validation | VE-001 | Validation errors occurred. | ValidationErrorResponse | This response will display the full list of validation exceptions on the requested form. |
|  