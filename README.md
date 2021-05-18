# nullable=false doesn't get mapped in orm.xml

## Problem

This example is about mapping a POJO (not an entity) via the `orm.xml` file[1].
The class `org.agoncal.bug.quarkus.orm.Artist` is just a POJO and cannot get used by the Panache repository `org.agoncal.bug.quarkus.orm.ArtistRepository`. 
For this to work we need to map it in the `META-INF/orm.xml` file.
The `ArtistRepositoryTest` checks that the mapping is working (inserting and retrieving data from a PostgreSQL database).

Thanks to the `scripts.generation.create-target` property, you can see the DDL in the file `create.sql`.
The mapping of the name attribute is:

```xml
<basic name="name">
  <column length="100" nullable="false"/>
</basic>
```

The DDL changes the length but not the nullability

```shell
    name         varchar(100),
```

### Quarkus SNAPSHOT

To reproduce this bug I am using a SNAPSHOT version of Quarkus.
You need to set `<quarkus-plugin.version>999-SNAPSHOT</quarkus-plugin.version>` in the `pom.xml`.

## References

* [1] https://github.com/quarkusio/quarkus/issues/14762
* [2] https://github.com/quarkusio/quarkus/issues/14762#issuecomment-843104842
