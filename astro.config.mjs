import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

export default defineConfig({
  site: 'https://caramelotech.com.br',
  base: '/clojure-labs',
  integrations: [
    starlight({
      title: 'Caramelo Tech',
      customCss: ['./src/styles/custom.css'],
      social: [
        {
          icon: 'github',
          label: 'GitHub',
          href: 'https://github.com/caramelotech',
        },
        {
          icon: 'linkedin',
          label: 'LinkedIn',
          href: 'https://www.linkedin.com/company/caramelotech/',
        },
        {
          icon: 'instagram',
          label: 'Instagram',
          href: 'https://www.instagram.com/caramelo_tech/',
        },
      ],
      defaultLocale: 'root',
      locales: {
        root: { label: 'Portugues', lang: 'pt-BR' },
      },
      sidebar: [
        {
          label: 'Introdução ao Clojure',
          autogenerate: { directory: '1-introducao' },
        },
        {
          label: 'Coleções',
          autogenerate: { directory: '2-colecoes' },
        },
        {
          label: 'Refs, Átomos e Concorrência',
          autogenerate: { directory: '3-refs' },
        },
        {
          label: 'Testes',
          autogenerate: { directory: 'x-tests' },
        },
      ],
    }),
  ],
});
