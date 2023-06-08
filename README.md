# GitHub Repository API

This API allows consumers to retrieve information about GitHub repositories based on the provided username.

## How to Use the API

### API Endpoint

The API endpoint for retrieving the list of GitHub repositories is:

```http request
GET /api/repositories/{username}
```

Replace {username} with the GitHub username for which you want to retrieve the repositories.

### Request Headers

* Accept: application/json - Set this header to receive the response in JSON format.

### Response

The API will respond with a JSON object containing the requested information.

```json
{
    "repositories": [
        {
            "name": "Repository 1",
            "owner": "username",
            "branches": [
                {
                    "name": "branch1",
                    "last_commit_sha": "abc123"
                },
                {
                    "name": "branch2",
                    "last_commit_sha": "def456"
                }
            ]
        },
        {
            "name": "Repository 2",
            "owner": "username",
            "branches": [
                {
                    "name": "branch1",
                    "last_commit_sha": "ghi789"
                }
            ]
        }
    ]
}
```

Example response for a non-existing GitHub user:

```json
{
    "status": 404,
    "Message": "User not found"
}
```

Example response when using an unsupported content type:

```json
{
  "status": 406,
  "Message": "Unsupported content type"
}
```

## Installation

1. Clone the repository:
    ```
    git clone https://github.com/Rafal354/GitHub-Repository-API.git
    ```

2. Navigate to the project directory:
    ```
    cd GitHub-Repository-API
    ```
   
3. Build the project using Maven:
    ```
   mvn clean install
    ```
   
## Usage
```
java -jar target/GitHub-Repository-API.jar
```
   
## License

This project is licensed under the MIT License.
