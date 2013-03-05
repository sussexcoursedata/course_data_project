Course Data Project
===================

Source code for University of Sussex XCRI-CAP Project
-----------------------------------------------------

Link to the Unversity of Sussex Blog page [course data blog](http://blogs.sussex.ac.uk/coursedata/).

The project includes a configurable interface to load from your own student record system. To use this implement the class uk.ac.susx.xcricap.session.SchemaLoaderBeanRemote (either add to this project or create a separate one). Then modify ejb-jar.xml and change the parameter for 'SchemaLoaderBeanName' to match the jndi lookup for your bean that implements the interface.

Alternatively, use a different method to populate your XCRI-CAP schema and change the 'useLoaderBean' parameter to false. This means the project will not attempt to populate your XCRI-CAP schema using the SchemaLoaderBeanRemote interface.

Licence
-------

This software is copyright (c) 2013 University of Sussex. It is made available for use under the MIT License. Further details can be found [here](LICENSE).