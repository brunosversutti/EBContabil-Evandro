package br.pro.delfino.drogaria2.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.pro.delfino.drogaria.bean.AutenticacaoBean;
import br.pro.delfino.drogaria.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		// System.out.println("DEPOIS DA FASE: " + event.getPhaseId());
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");

		if (!ehPaginaDeAutenticacao) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			if (autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			Usuario usuario = autenticacaoBean.getUsuario();
			if (usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// System.out.println("ANTES DA FASE: " + event.getPhaseId() );

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
