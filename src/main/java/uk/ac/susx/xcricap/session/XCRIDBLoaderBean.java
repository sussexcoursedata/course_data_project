package uk.ac.susx.xcricap.session;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import uk.ac.susx.xcricap.model.XcriCatalog;

/**
 *
 * @author rjb41
 */
@Stateless
@LocalBean
public class XCRIDBLoaderBean implements XCRIDBLoaderBeanRemote {

    @PersistenceContext(unitName = "XCRI-CAPPU")
    private EntityManager em;
    static final Logger log = Logger.getLogger(XCRIDBLoaderBean.class.getName());

    @Override
    public List<XcriCatalog> loadCatalogs(String catalogType) {
        List<XcriCatalog> catalogs = null;

        try {
            catalogs = em.createNamedQuery("XcriCatalog.findByCatalogType").setParameter("catalogType", catalogType).getResultList();
        } catch (NoResultException e) {
        } 

        return catalogs;
    }
}
