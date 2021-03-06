package br.com.pedroyodasaito.vendas.service.pedido;

import br.com.pedroyodasaito.vendas.domain.entity.ItemPedido;
import br.com.pedroyodasaito.vendas.domain.entity.Pedido;
import br.com.pedroyodasaito.vendas.domain.enums.StatusPedido;
import br.com.pedroyodasaito.vendas.domain.repository.ClienteRepository;
import br.com.pedroyodasaito.vendas.domain.repository.ItemPedidoRepository;
import br.com.pedroyodasaito.vendas.domain.repository.PedidoRepository;
import br.com.pedroyodasaito.vendas.domain.repository.ProdutoRepository;
import br.com.pedroyodasaito.vendas.exception.PedidoNaoEncontradoException;
import br.com.pedroyodasaito.vendas.exception.RegraNegocioException;
import br.com.pedroyodasaito.vendas.service.pedido.dto.InformacoesItemPedidoDTO;
import br.com.pedroyodasaito.vendas.service.pedido.dto.ItemPedidoDTO;
import br.com.pedroyodasaito.vendas.service.pedido.dto.PedidoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
                             ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository
                .findById(pedidoDTO.getClienteID())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado")));
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setStatus(StatusPedido.REALIZADO);
        List<ItemPedido> listaItemPedido = converterListaItemPedidoDTOToListaItemPedido(pedido, pedidoDTO.getItensPedido());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(listaItemPedido);
        pedido.setItens(listaItemPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizarStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    pedidoRepository.save(pedido);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<InformacoesItemPedidoDTO> converterItemPedidoParaInformacoesItemPedidoDTO(List<ItemPedido> lista) {
        return lista.stream()
                .map(item -> {
                    InformacoesItemPedidoDTO informacoesItemPedidoDTO = new InformacoesItemPedidoDTO();
                    informacoesItemPedidoDTO.setDescricaoProduto(item.getProduto().getDescricao());
                    informacoesItemPedidoDTO.setPrecoUnitario(item.getProduto().getPreco());
                    informacoesItemPedidoDTO.setQuantidade(item.getQuantidade());
                    return informacoesItemPedidoDTO;
                }).collect(Collectors.toList());
    }

    private List<ItemPedido> converterListaItemPedidoDTOToListaItemPedido (Pedido pedido, List<ItemPedidoDTO> listaItemPedidoDTO) {
        if(listaItemPedidoDTO.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return listaItemPedidoDTO
                .stream()
                .map(itemPedidoDTO -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produtoRepository
                            .findById(itemPedidoDTO.getProdutoID())
                            .orElseThrow(() ->
                                    new RegraNegocioException("Produto de código " +
                                            itemPedidoDTO.getProdutoID() + " não encontrado")));
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
