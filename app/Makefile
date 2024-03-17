run-dist:
	./build/install/app/bin/app
build:
	gradle clean build
	gradle checkstyleMain
	gradle test
	gradle jacocoTestReport

.PHONY: build