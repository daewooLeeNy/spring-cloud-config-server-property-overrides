# Spring Cloud Config Example

Spring Cloud Config 설정 과정에서 client의 로컬 properties를 사용 하기 위한 설정을 설명 합니다.

아래의 링크에서 설명 하고 있으나 처음 셋팅 하는 경우 설정하는 과정에서 혼란을 겪는 경우가 있어(경험담.)

테스트 할 수 있는 project가 있었으면 조금 더 쉽게 파악 할 수 있었을 거 같아 공유 합니다

## Property Overrides
아래와 같이 spring config reference에서 설명 하고 있습니다

> You can change the priority of all overrides in the client to be more like default values, allowing applications to supply their own values in environment variables or System properties, by setting the flag spring.cloud.config.overrideNone=true (default is false) in the remote repository.

[Property Overrides in Spring Cloud Config Reference](http://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.0.0.M5/single/spring-cloud-config.html#_property_overrides)

이렇게 설명 하고 있음에도 안드로메다로 향합니다.
그 이유는 Spring Cloud Config Server가 remote repository(Git, Svn, etc) 등을 통해 특정 application properties을 전달 받고
이 properties에는 spring.cloude.config 로 시작 하는 속성도 포함 하고 있기 때문입니다.

즉, 'remote repository'로 명시 되어 있듯이 Spring Colud Config Server가 참조 하고 있는 repository에
application name을 prefix로 하는 설정 파일(properties, yml)에 spring.cloud.config.overrideNone=true 속성이 지정 되어 있어야 합니다

http://localhost:8888/foo 로 요청 할 경우 (application name=foo) repository의 foo.properties에 아래와 같이 속성이 지정 되어야 합니다.

```
spring.cloud.config.overrideNone: true
```
[foo.properties](/src/resources/config-repo/foo.properties)

이 프로젝트에서는 테스트의 편의를 위해 Config Server의 로컬 파일에 foo.properties를 위치 시키고 읽어들입니다.
로컬 파일 사용을 위해 아래와 같은 설정을 추가 하였습니다.

```
spring.profiles.active=native
spring.cloud.config.server.native.searchLocations=classpath:/config-repo
```
[bootstrap.yml](/src/resources/bootstrap.xml)

client project를 이용 해서 local properties가 정상적으로 처리 되는지 확인 합니다.
[spring-cloud-client-overrides]()

## 참고
- http://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.0.0.M5/single/spring-cloud-config.html#_property_overrides
- https://github.com/spring-cloud/spring-cloud-config/issues/573
