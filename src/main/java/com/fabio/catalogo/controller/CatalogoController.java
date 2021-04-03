package com.fabio.catalogo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.fabio.catalogo.model.Musica;
import com.fabio.catalogo.service.CatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CatalogoController {
    
    @Autowired CatalogoService catalogoService;

    @RequestMapping(value="/musicas", method=RequestMethod.GET)
    public ModelAndView getMusicas() {
        ModelAndView mv = new ModelAndView("musicas");
        List<Musica> musicas = catalogoService.findAll();
        mv.addObject("musicas", musicas);
        return  mv;
    }
    
    @RequestMapping(value="/musicas/{id}", method=RequestMethod.GET)
    public ModelAndView getMusicasDetalhes(@PathVariable("id") long id) {
       ModelAndView mv = new ModelAndView("detalhes");
        Musica musica = catalogoService.findById(id);
        mv.addObject("musica", musica);
        return  mv;
    }

    @RequestMapping(value="/adicionarMusica", method=RequestMethod.GET)
    public String getForm() {
        return "adicionar";
    }
    
    @RequestMapping(value="/adicionarMusica", method=RequestMethod.POST)
    public String salvarMusica(@Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        musica.setData(LocalDate.now());
        catalogoService.save(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value="/editarMusica/{id}", method=RequestMethod.GET)
    public ModelAndView getEditarMusica(@PathVariable("id") long id) {
       ModelAndView mv = new ModelAndView("editar");
        Musica musica = catalogoService.findById(id);
        mv.addObject("musica", musica);
        return  mv;
    }

    @RequestMapping(value="/editarMusica/{id}", method=RequestMethod.POST)
    public String editarMusica(@PathVariable("id") long id, @Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        musica.setId(id);
        musica.setData(LocalDate.now());
        catalogoService.save(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
    public String getExcluirMusicas(@PathVariable("id") long id) {
        catalogoService.excluir(id);
        return "redirect:/musicas";
    }
    
}
