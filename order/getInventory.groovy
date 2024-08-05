package contracts.rest

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url ('/inventories/search/findByTestInventory')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
                id: 1,
                stock: 10,
        )
        bodyMatchers {
            jsonPath('$[0].id', byRegex(nonEmpty()).asLong())
            jsonPath('$[0].stock', byRegex(nonEmpty()).asInteger())
        }
        headers {
            contentType(applicationJson())
        }
    }
}
