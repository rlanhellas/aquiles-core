package br.com.aquiles.security.util;

import br.com.aquiles.security.bean.Documento;
import br.com.aquiles.security.bean.Transacao;
import br.com.aquiles.security.dto.AutorizacaoDTO;
import br.com.aquiles.security.dto.MenuChild;
import br.com.aquiles.security.dto.MenuParent;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class build the Menu based on authorizations
 * @author Ronaldo Lanhellas
 */
public class MenuUtils {

    public static List<MenuParent> construirMenus(List<AutorizacaoDTO> autorizacoes) {
        List<MenuParent> menus = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            menus.add(null);
        }
        List<String> docsInseridos = new ArrayList<>();
        for (AutorizacaoDTO auto : autorizacoes) {

            if (auto.getSgDoc().contains("****")) {
                continue;
            }

            Documento doc = auto.getDocumento();

            //if property nrOrdWeb is null, then should skip this item, not build it.
            if (doc.getNrOrdWeb() == null){
                continue;
            }

            String awesomeIcone = "";
            if (doc.getIcone() == null || (!Hibernate.isInitialized(doc.getIcone()))) {
                awesomeIcone = "fa fa-question";
            } else {
                awesomeIcone = doc.getIcone().getAwesomeClass();
            }

            if (!docsInseridos.contains(auto.getSgDoc())) {
                if (!auto.isEsconderMenu()) {
                    menus.set(doc.getNrOrdWeb(), new MenuParent(doc.getDeDoc(), auto.getSgDoc(),
                            auto.getSgTrn(), doc.getNrOrdWeb(), awesomeIcone,
                            getChilds(auto.getSgDoc(), autorizacoes)));
                }
                docsInseridos.add(auto.getSgDoc());
            }
        }

        return searchForDOCToActAsTRAN(menus);
    }

    private static List<MenuChild> getChilds(String sgDoc, List<AutorizacaoDTO> autorizacaos) {
        List<MenuChild> childs = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            childs.add(null);
        }

        for (AutorizacaoDTO auto : autorizacaos) {
            if (auto.getSgTrn().contains("****")) {
                continue;
            }
            Transacao tran = auto.getTransacao();

            if (tran.getNrOrdWeb() == null){
                continue;//se o nrOrdWeb estiver nulo então não deverá ser montado como MENU
            }

            if (auto.getSgDoc().equalsIgnoreCase(sgDoc) && !childs.contains(new MenuChild(auto.getSgTrn()))) {
                if (!auto.isEsconderMenu()) {
                    childs.set(tran.getNrOrdWeb(), new MenuChild(tran.getDeTrn(), auto.getSgTrn(),
                            tran.getNrOrdWeb()));
                }
            }
        }

        return cleanEmptyChildCell(childs);
    }

    private static List<MenuChild> cleanEmptyChildCell(List<MenuChild> childs) {
        List<MenuChild> menusChilds = new ArrayList<>();
        for (MenuChild child : childs) {
            if (child != null) {
                menusChilds.add(child);
            }
        }

        return menusChilds;
    }

    /**
     * Search for any DOC that contains only one child (TRAN) and this TRAN have the same name as DOC. When found
     * the 'docAsTran' property is activated.
     *
     * This method also clean all "null" items the was produced by others methods.
     * */
    private static List<MenuParent> searchForDOCToActAsTRAN(List<MenuParent> menus){
        List<MenuParent> menuAfter = new ArrayList<>();
        for(MenuParent menu : menus){
            if (menu != null) {
                if (menu.getChilds()!=null && menu.getChilds().size()==1 &&
                        menu.getChilds().get(0).getSiglaTran().equals(menu.getSiglaDoc())){
                    menu.setDocAsTran(true);
                }

                menuAfter.add(menu);
            }
        }

        return menuAfter;
    }

}
