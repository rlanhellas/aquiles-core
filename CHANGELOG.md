# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2019-01-13
### Added
- Create a Listener in AbstractEntityAtu to watch any persist or update, adding correct user and timestamp that start 
this event
- Suport do Cycle reference with @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class) using 
Jackson 2.x
