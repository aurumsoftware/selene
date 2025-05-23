package com.aurum.services.microservice.application.security.exception


class NamespaceNotFoundException : RuntimeException("Namespace not found in current Token!")